package com.fuzamei.utils.redis;

import redis.clients.jedis.*;

/**
 * Created by fuzamei on 2018/7/26.
 */
public class RedisExecutor {

    private static JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(2);
        config.setMinIdle(2);
        config.setTestOnBorrow(true);
        config.setMaxWaitMillis(10000);
        config.setMinIdle(5);

        pool = new JedisPool(config, "120.55.54.53", 6379, 10000, "contractchain", 1);
    }

    private static Jedis jedis = pool.getResource();


    public static void main(String[] args) {
        try {
//            Long hahaha = jedis.setnx("hahaha", "1");
//            System.out.println(hahaha);
            String set = jedis.set("haha", "jj", "NX", "EX", 200L);
            System.out.println(set);
        } catch (Exception e){
            System.out.println("出现异常");
            e.printStackTrace();
        }finally {
            System.out.println("程序结束");
            jedis.close();
        }
    }
}
