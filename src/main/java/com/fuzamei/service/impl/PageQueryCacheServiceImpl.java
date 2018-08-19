package com.fuzamei.service.impl;

import com.alibaba.fastjson.JSON;
import com.fuzamei.mapper.PageQueryMapper;
import com.fuzamei.model.QueryBO;
import com.fuzamei.model.UserDO;
import com.fuzamei.service.PageQueryService;
import com.fuzamei.utils.page.PageDTO;
import com.fuzamei.utils.redis.RedisKeyUtils;
import com.fuzamei.utils.redis.RedisTimeUtil;
import com.fuzamei.utils.redis.RedisUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ylx
 * Created by fuzamei on 2018/8/17.
 */
@Slf4j
@Service("useRedis")
public class PageQueryCacheServiceImpl implements PageQueryService{

    @Autowired
    private PageQueryMapper pageQueryMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    @Qualifier("noRedis")
    private PageQueryService pageQueryService;


    @Override
    public PageDTO query(QueryBO queryBO) {
        final Jedis jedis = jedisPool.getResource();
        try {
            String redisUsername = queryBO.getRedisUsername();
            String redisAddress = queryBO.getRedisAddress();
            Long redisStartPrice = queryBO.getRedisStartPrice();
            Long redisEndPrice = queryBO.getRedisEndPrice();
            Long redisStartTime = queryBO.getRedisStartTime();
            Long redisEndTime = queryBO.getRedisEndTime();
            Integer startPage = queryBO.getStartPage();
            Integer rowNum = queryBO.getRowNum();

            Long startPrice = queryBO.getStartPrice();
            Long endPrice = queryBO.getEndPrice();
            Long startTime = queryBO.getStartTime();
            Long endTime = queryBO.getEndTime();

            String timeKey = RedisKeyUtils.userQueryUtimeSearch(startTime,endTime);
            String priceKey = RedisKeyUtils.userQueryPriceSearch(startPrice,endPrice);
            String addressKey = RedisKeyUtils.userQueryAddressSearch(redisAddress);
            String usernameKey = RedisKeyUtils.userQueryUsernameSearch(redisUsername);

            boolean usernameFlag = false;
            boolean addressFlag = false;
            boolean priceFlag = false;
            boolean timeFlag = false;

            //综合搜索的key--->之前如果搜索过有的话，直接拿即可
            String complexKey = RedisKeyUtils.userQueryAllSearch(redisUsername, redisAddress, startPrice, endPrice, startTime, endTime);
            if(jedis.exists(complexKey)){
                log.info("已经存在综合key缓存，直接查即");
                return getPage(jedis,startPage,rowNum,queryBO,startTime,endTime,complexKey);
            }

            //说明用户输入了用户名搜索
            if(!StringUtils.isEmpty(redisUsername)){
                log.info("说明用户输入了用户名搜索");
                if(!jedis.exists(usernameKey)){
                    String[] values = redisUtil.hscan(RedisKeyUtils.userQueryUsername(), "*" + redisUsername + "*");
                    if(values.length == 0){
                        return PageDTO.getPagination(0, Collections.emptyList());
                    }
                    jedis.sadd(usernameKey,values);
                    //设置10分钟的过期时间
                    jedis.pexpire(usernameKey,RedisTimeUtil.TEN_MINUTES);
                }
                usernameFlag = true;
            }

            //说明用户输入了地址搜索
            if(!StringUtils.isEmpty(redisAddress)){
                log.info("说明用户输入了地址搜索");
                if(!jedis.exists(addressKey)){
                    String[] values = redisUtil.hscan(RedisKeyUtils.userQueryAddress(), "*:*" + redisAddress + "*");
                    if(values.length == 0){
                        return PageDTO.getPagination(0, Collections.emptyList());
                    }
                    jedis.sadd(addressKey,values);
                    //设置10分钟的过期时间
                    jedis.pexpire(addressKey,RedisTimeUtil.TEN_MINUTES);
                }
                addressFlag = true;
            }

            //说明用户选择了价格区间搜索
            if(redisStartPrice != null || redisEndPrice != null){
                log.info("说明用户选择了价格区间搜索");
                if(!jedis.exists(priceKey)){
                    Set<String> set = jedis.zrangeByScore(RedisKeyUtils.userQueryPrice(), startPrice, endPrice);
                    if(set.size() == 0){
                        return PageDTO.getPagination(0, Collections.emptyList());
                    }
                    jedis.sadd(priceKey,set.toArray(new String[set.size()]));
                    //设置10分钟的过期时间
                    jedis.pexpire(priceKey,RedisTimeUtil.TEN_MINUTES);
                }
                priceFlag = true;
            }

            //说明用户选择了时间区间搜索
            if(redisStartTime != null || redisEndTime != null){
                log.info("说明用户选择了时间区间搜索");
                if(!jedis.exists(timeKey)){
                    Set<Tuple> set = jedis.zrangeByScoreWithScores(RedisKeyUtils.userQueryUtime(), startTime, endTime);
                    if(set.size() == 0){
                        return PageDTO.getPagination(0, Collections.emptyList());
                    }
                    final Map<String,Double> map = new HashMap<>(set.size());
                    set.stream().forEach(x->map.put(x.getElement(),x.getScore()));
                    jedis.zadd(timeKey,map);
                    //设置10分钟的过期时间
                    jedis.pexpire(timeKey,RedisTimeUtil.TEN_MINUTES);
                }
                timeFlag = true;
            }

            //以下正式开始搜索

            //最简单的一种情况，用户啥都没输入，就按照时间倒序排序为准
            if(!usernameFlag && !addressFlag && !priceFlag && !timeFlag){
                log.info("最简单的一种情况，用户啥都没输入，就按照时间倒序排序为准");
                return getPage(jedis,startPage,rowNum,queryBO,startTime,endTime,RedisKeyUtils.userQueryUtime());
            }

            log.info("usernameFlag:"+usernameFlag+",addressFlag:"+addressFlag+",priceFlag:"+priceFlag+",timeFlag:"+timeFlag);

            ZParams zParams = new ZParams();
            zParams.aggregate(ZParams.Aggregate.SUM);
            List<Double> weights = new ArrayList<>();
            List<String> sets = new ArrayList<>();
            if(timeFlag){
                weights.add(1d);
                sets.add(timeKey);
            }else{
                weights.add(1d);
                sets.add(RedisKeyUtils.userQueryUtime());
            }
            if(priceFlag){
                weights.add(0d);
                sets.add(priceKey);
            }
            if(addressFlag){
                weights.add(0d);
                sets.add(addressKey);
            }
            if(usernameFlag){
                weights.add(0d);
                sets.add(usernameKey);
            }
            double[] doubles = new double[weights.size()];
            for (int i = 0;i<weights.size();i++){
                doubles[i] = weights.get(i);
            }
            zParams.weightsByDouble(doubles);
            jedis.zinterstore(complexKey,zParams,sets.toArray(new String[sets.size()]));
            jedis.pexpire(complexKey,RedisTimeUtil.TEN_MINUTES);
            return getPage(jedis,startPage,rowNum,queryBO,startTime,endTime,complexKey);
        }finally {
            jedis.close();
        }
    }

    private PageDTO getPage(Jedis jedis,Integer startPage,Integer rowNum,QueryBO queryBO,Long startTime,Long endTime,String key){
        //总页数
        long total = jedis.zcount(key, startTime, endTime);
        if(total == 0){
            return PageDTO.getPagination((int)total,Collections.emptyList());
        }
        Set<String> idStrings = jedis.zrevrangeByScore(key, endTime, startTime, startPage, rowNum);
        final List<String> keyStrings = new ArrayList<>(idStrings.size());
        idStrings.stream().forEach(x->keyStrings.add(RedisKeyUtils.userQueryData(x)));
        List<String> mget = jedis.mget(keyStrings.toArray(new String[keyStrings.size()]));

        //循环遍历检查是否有空的json串，如果有就重新去数据库查询一遍(但是为了防止缓存击穿，做了双端检测)
        for (String jsonString : mget){
            //防止缓存击穿的双端检测
            if(StringUtils.isEmpty(jsonString)){
                List<UserDO> rows = null;
                synchronized (PageQueryCacheServiceImpl.class){
                    if(rows == null){
                        rows = pageQueryMapper.query(queryBO);
                        rows.stream().forEach(x->jedis.psetex(RedisKeyUtils.userQueryData(x.getId()), RedisTimeUtil.ONE_DAY,JSON.toJSONString(x)));
                    }
                }
                log.info("查询数据库之后，存入缓存后再返回");
                return PageDTO.getPagination((int)total,rows);
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append('[');
        mget.stream().forEach(x->sb.append(x).append(','));
        sb.replace(sb.length()-1,sb.length(),"]");
        List<UserDO> rows = JSON.parseArray(sb.toString(), UserDO.class);
        log.info("直接返回缓存的数据");
        return PageDTO.getPagination((int)total,rows);
    }



    @Override
    public void insert(QueryBO queryBO) {
        pageQueryService.insert(queryBO);
        String username = queryBO.getUsername();
        String password = queryBO.getPassword();
        String address = queryBO.getAddress();
        Long utime = queryBO.getUtime();
        Long price = queryBO.getPrice();
        Long id = queryBO.getId();
        UserDO userDO = UserDO.builder().address(address).ctime(utime).utime(utime).username(username).password(password).id(id).price(price).build();
        final Jedis jedis = jedisPool.getResource();
        //使用redis的管道技术 todo ☆
        final Pipeline pipelined = jedis.pipelined();
        try {
            //redis中建立username的索引
            pipelined.hset(RedisKeyUtils.userQueryUsername(),username,String.valueOf(id));
            //redis中建立address的索引
            pipelined.hset(RedisKeyUtils.userQueryAddress(),String.valueOf(id)+":"+address,String.valueOf(id));
            //redis中建立price的索引
            pipelined.zadd(RedisKeyUtils.userQueryPrice(),price,String.valueOf(id));
            //redis中建立utime的索引
            pipelined.zadd(RedisKeyUtils.userQueryUtime(),utime,String.valueOf(id));
            //顺便插一条数据进去 todo ☆
            pipelined.psetex(RedisKeyUtils.userQueryData(id), RedisTimeUtil.ONE_DAY,JSON.toJSONString(userDO));

            pipelined.sync();
        }finally {
            jedis.close();
        }
    }

    @Override
    public void delete(QueryBO queryBO) {
        List<UserDO> deletes = pageQueryMapper.queryDelete(queryBO);
        pageQueryService.delete(queryBO);
        final Jedis jedis = jedisPool.getResource();
        //使用redis的管道技术
        final Pipeline pipelined = jedis.pipelined();
        try {
            //以下是删除redis中存在的索引
            deletes.stream().forEach(x->{
                System.out.println("开始清理缓存数据");
                pipelined.hdel(RedisKeyUtils.userQueryUsername(),x.getUsername());
                pipelined.hdel(RedisKeyUtils.userQueryAddress(),String.valueOf(x.getId())+":"+x.getAddress());
                pipelined.zrem(RedisKeyUtils.userQueryPrice(),String.valueOf(x.getId()));
                pipelined.zrem(RedisKeyUtils.userQueryUtime(),String.valueOf(x.getId()));
                //todo 还有可能残留的数据 todo ☆
                pipelined.del(RedisKeyUtils.userQueryData(x.getId()));
            });
            pipelined.sync();
        }finally {
            jedis.close();
        }

        //todo 如果是针对个人的话，这里可以对个人搜索的缓存也一并进行删除，保证数据的一致性
    }

    @Override
    public void update(QueryBO queryBO) {
        Long id = queryBO.getId();

        String stringUserDO = redisUtil.get(RedisKeyUtils.userQueryData(id));
        UserDO userDO;
        if(StringUtils.isEmpty(stringUserDO)){
            userDO = pageQueryMapper.findDataById(queryBO);
        }else{
            userDO = JSON.parseObject(stringUserDO,UserDO.class);
        }
        if(null == userDO){
            throw new RuntimeException("id号不存在");
        }

        //todo 真正对数据库进行修改
        pageQueryService.update(queryBO);


        final Jedis jedis = jedisPool.getResource();
        //使用redis的管道技术 todo ☆
        Pipeline pipelined = jedis.pipelined();
        try {
            if(!StringUtils.isEmpty(queryBO.getUsername())){
                //删除原来的索引
                pipelined.hdel(RedisKeyUtils.userQueryUsername(),userDO.getUsername());
                //建立新的索引
                pipelined.hset(RedisKeyUtils.userQueryUsername(),queryBO.getUsername(),String.valueOf(id));
            }
            if(!StringUtils.isEmpty(queryBO.getAddress())){
                //删除老地址
                pipelined.hdel(RedisKeyUtils.userQueryAddress(),String.valueOf(id)+":"+userDO.getAddress());
                //增加新地址
                pipelined.hset(RedisKeyUtils.userQueryAddress(),String.valueOf(id)+":"+queryBO.getAddress(),String.valueOf(id));
            }
            if(queryBO.getPrice() != null){
                //替换老价格为新价格
                pipelined.zadd(RedisKeyUtils.userQueryPrice(),queryBO.getPrice(),String.valueOf(id));
            }
            //替换新的更新时间
            pipelined.zadd(RedisKeyUtils.userQueryUtime(),queryBO.getUtime(),String.valueOf(id));

            //todo 如果有数据缓存，直接删除 todo ☆
            pipelined.del(RedisKeyUtils.userQueryData(id));

            pipelined.sync();
        }finally {
            jedis.close();
        }
    }
}
