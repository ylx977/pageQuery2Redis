package com.fuzamei.utils.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ylx
 * Created by fuzamei on 2018/4/22.
 */
@Component
@Slf4j
public class RedisUtil {

    private final JedisPool jedisPool;

    @Autowired
    public RedisUtil(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    //=============================common============================
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间 long类型
     * @param timeUnit 时间单位
     * @return
     */
    public final boolean expire(String key,long time,TimeUnit timeUnit){
        Jedis jedis = jedisPool.getResource();
        try {
            if(timeUnit.equals(TimeUnit.SECONDS)){
                if(time > 0){
                    jedis.pexpire(key,time*1000);
                    return true;
                }
            }
            if(timeUnit.equals(TimeUnit.MILLISECONDS)){
                if(time > 0){
                    jedis.pexpire(key,time);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            log.error("设置redis失效时间出现异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long ttl(String key){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("从redis获取失效时间出现异常");
            return 0L;
        }finally {
            closeGracefully(jedis);
        }
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean exists(String key){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            log.error("redis判断是否有key值出现异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    /**
     * 删除缓存 (批量的key值删除)
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void delete(String ... key){
        Jedis jedis = jedisPool.getResource();
        try {
            if(key != null && key.length > 0){
                if(key.length == 1){
                    jedis.del(key[0]);
                }else{
                    jedis.del(key);
                }
            }
        } catch (Exception e) {
            log.error("redis删除key值出现异常");
        }finally {
            closeGracefully(jedis);
        }
    }

    //============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public String get(String key){
        Jedis jedis = jedisPool.getResource();
        try {
            return key == null ? null : jedis.get(key);
        } catch (Exception e) {
            log.error("redis获取key对应的value值出现异常");
            return null;
        }finally {
            closeGracefully(jedis);
        }
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key,String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis插入key-value对出现异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key,String value,long time,TimeUnit timeUnit){
        Jedis jedis = jedisPool.getResource();
        try {
            if(null != timeUnit){
                if(time > 0){
                    if(timeUnit.equals(TimeUnit.DAYS)){
                        jedis.psetex(key,time*24L*3600*1000,value);
                    }
                    if(timeUnit.equals(TimeUnit.MILLISECONDS)){
                        jedis.psetex(key,time,value);
                    }
                    if(timeUnit.equals(TimeUnit.SECONDS)){
                        jedis.psetex(key,1000L*time,value);
                    }
                    if(timeUnit.equals(TimeUnit.HOURS)){
                        jedis.psetex(key,1000L*3600*time,value);
                    }
                    if(timeUnit.equals(TimeUnit.MINUTES)){
                        jedis.psetex(key,1000L*60*time,value);
                    }
                    if(timeUnit.equals(TimeUnit.MICROSECONDS)){
                        jedis.psetex(key,time/1000,value);
                    }
                    if(timeUnit.equals(TimeUnit.NANOSECONDS)){
                        jedis.psetex(key,time/1000*1000,value);
                    }
                }else{
                    set(key, value);
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("redis插入key-value对出现异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    /**
     * 自增1
     * @param key
     * @return
     */
    public boolean incr(String key){
        Jedis jedis = jedisPool.getResource();
        try {
            Long increment = jedis.incr(key);
            if(!increment.equals(1L)){
                throw new RuntimeException();
            }
            return true;
        }catch (Exception e){
            log.error("redis自增出现异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    /**
     * 自增数量自定义
     * @param key
     * @param value
     * @return
     */
    public boolean incrBy(String key,long value){
        Jedis jedis = jedisPool.getResource();
        try {
            Long increment = jedis.incrBy(key, value);
            if(!increment.equals(value)){
                throw new RuntimeException();
            }
            return true;
        }catch (Exception e){
            log.error("redis自增出现异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    /**
     * 获取多个数值
     * @param keys
     * @return
     */
    public List<String> mget(String...keys){
        Jedis jedis = jedisPool.getResource();
        try {
            List<String> mget = jedis.mget(keys);
            return mget;
        }catch (Exception e){
            return Collections.emptyList();
        }finally {
            closeGracefully(jedis);
        }
    }

    public boolean sadd(String key,String... values){
        Jedis jedis = jedisPool.getResource();
        try {
            Long sadd = jedis.sadd(key, values);
            if(sadd == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }


    public Long zadd(String key,double score,String member){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zadd(key,score,member);
        }catch (Exception e){
            log.error("redis添加zadd异常");
            return 0L;
        }finally {
            closeGracefully(jedis);
        }
    }

    public Long zcard(String key){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zcard(key);
        }catch (Exception e){
            log.error("redis获取zcard异常");
            return 0L;
        }finally {
            closeGracefully(jedis);
        }
    }

    public boolean zrem(String key,String... members){
        Jedis jedis = jedisPool.getResource();
        try {
            Long zrem = jedis.zrem(key, members);
            if(zrem != 0){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    public Set<String> zrangeByScore(String key, double min, double max){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrangeByScore(key,min,max);
        }catch (Exception e){
            log.error("redis获取zrangeByScore异常");
            return Collections.emptySet();
        }finally {
            closeGracefully(jedis);
        }
    }

    public Set<String> zrevrangeByScore(String key, double max, double min){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrevrangeByScore(key,max,min);
        }catch (Exception e){
            log.error("redis获取zrevrangeByScore异常");
            return Collections.emptySet();
        }finally {
            closeGracefully(jedis);
        }
    }

    public Set<String> zrange(String key,long start,long end){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrange(key,start,end);
        }catch (Exception e){
            log.error("redis获取zrange异常");
            return Collections.emptySet();
        }finally {
            closeGracefully(jedis);
        }
    }

    public Set<String> zrevrange(String key,long start,long end){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrevrange(key,start,end);
        }catch (Exception e){
            log.error("redis获取zrevrange异常");
            return Collections.emptySet();
        }finally {
            closeGracefully(jedis);
        }
    }

    public Set<String> zrangeByScore(String key,double min,double max,int offset,int count){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrangeByScore(key,min,max,offset,count);
        }catch (Exception e){
            log.error("redis获取zrangeByScore异常");
            return Collections.emptySet();
        }finally {
            closeGracefully(jedis);
        }
    }

    public Set<Tuple> zrangeByScoreWithScore(String key, double min, double max, int offset, int count){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrangeByScoreWithScores(key,min,max,offset,count);
        }catch (Exception e){
            log.error("redis获取zrangeByScoreWithScore异常");
            return Collections.emptySet();
        }finally {
            closeGracefully(jedis);
        }
    }

    public Set<String> zrevrangeByScore(String key,double max,double min,int offset,int count){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrevrangeByScore(key,max,min,offset,count);
        }catch (Exception e){
            log.error("redis获取zrevrangeByScore异常");
            return Collections.emptySet();
        }finally {
            closeGracefully(jedis);
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScore(String key, double max, double min, int offset, int count){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrevrangeByScoreWithScores(key,max,min,offset,count);
        }catch (Exception e){
            e.printStackTrace();
            log.error("redis获取zrevrangeByScoreWithScore异常");
            return Collections.emptySet();
        }finally {
            closeGracefully(jedis);
        }
    }

    public long zcount(String key,double min,double max){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zcount(key,min,max);
        }catch (Exception e){
            log.error("redis获取zcount异常");
            return 0;
        }finally {
            closeGracefully(jedis);
        }
    }

    public boolean zinterStore(String sdtKey,String...sets){
        Jedis jedis = jedisPool.getResource();
        try {
            Long zinterstore = jedis.zinterstore(sdtKey, sets);
            return zinterstore.equals(0L);
        }catch (Exception e){
            log.error("redis获取zinterStore异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    public boolean zinterStore(String sdtKey,ZParams zParams,String...sets){
        Jedis jedis = jedisPool.getResource();
        try {
            Long zinterstore = jedis.zinterstore(sdtKey,zParams, sets);
            return zinterstore.equals(0L);
        }catch (Exception e){
            log.error("redis获取zinterStore异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    /**
     * set一个key,如果key存在redis会返回0，如果key不存在，返回1
     * @param key
     * @param value
     * @return
     */
    public boolean setNx(String key,String value){
        Jedis jedis = jedisPool.getResource();
        try {
            String nx = jedis.set(key, value, "NX");
//            long success = jedis.setnx(key, value);
            if(nx!=null){
                return true;
            }
            return false;
        }catch (Exception e){
            log.error("redis加锁异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    /**
     * stxNx
     * @return
     */
    public boolean setNx(String key,String value,long expire){
        Jedis jedis = jedisPool.getResource();
        try {
            String lock = jedis.set(key, value, "NX", "EX", expire);
            if(lock != null){
                return true;
            }
            return false;
        }catch (Exception e){
            log.error("redis加锁异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    public boolean hmset(String key, Map<String,String> hash){
        Jedis jedis = jedisPool.getResource();
        try {
            String hmset = jedis.hmset(key, hash);
            if(hmset!=null){
                return true;
            }
            return false;
        }catch (Exception e){
            log.error("redis在hmset异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    public boolean hset(String key, String filed,String value){
        Jedis jedis = jedisPool.getResource();
        try {
            Long hset = jedis.hset(key, filed, value);
            if(hset!=null && !hset.equals(0L)){
                return true;
            }
            return false;
        }catch (Exception e){
            log.error("redis在hset异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    public boolean hdel(String key, String... fileds){
        Jedis jedis = jedisPool.getResource();
        try {
            Long hset = jedis.hdel(key, fileds);
            if(hset!=null && !hset.equals(0L)){
                return true;
            }
            return false;
        }catch (Exception e){
            log.error("redis在hdel异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }

    public String[] hscan(String key,String pattern){
        Jedis jedis = jedisPool.getResource();
        try {
            ScanParams scanParams = new ScanParams();
            scanParams.match(pattern);
            Set<String> set = new HashSet<>();
            while(true){
                ScanResult<Map.Entry<String, String>> hscan = jedis.hscan(key, "0", scanParams);
                List<Map.Entry<String, String>> result = hscan.getResult();
                result.stream().forEach(x->set.add(x.getValue()));
                String stringCursor = hscan.getStringCursor();
                if("0".equals(stringCursor)){
                    break;
                }
            }
            if(set.size()==0){
                return new String[0];
            }
            return set.toArray(new String[set.size()]);
        }catch (Exception e){
            return new String[0];
        }finally {
            closeGracefully(jedis);
        }
    }

    public String hget(String key,String field){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hget(key, field);
        }catch (Exception e){
            log.error("redis在hget异常");
            return null;
        }finally {
            closeGracefully(jedis);
        }
    }

    public List<String> hmget(String key,String...fields){
        Jedis jedis = jedisPool.getResource();
        try {
            List<String> hmget = jedis.hmget(key, fields);
            return hmget;
        }catch (Exception e){
            log.error("redis在hmget异常");
            return null;
        }finally {
            closeGracefully(jedis);
        }
    }

    public boolean hexists(String key,String field){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hexists(key, field);
        }catch (Exception e){
            log.error("redis在hexists异常");
            return false;
        }finally {
            closeGracefully(jedis);
        }
    }


    private void closeGracefully(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }

}
