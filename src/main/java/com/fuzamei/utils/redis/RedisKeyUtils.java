package com.fuzamei.utils.redis;

/**
 * @author ylx
 * Created by fuzamei on 2018/8/18.
 */
public class RedisKeyUtils {

    /**
     * 非唯一索引以hash表存
     * @return
     */
    public static String userQueryAddress(){
        return "user_query:idx:address";
    }

    /**
     * 唯一索引以hash存
     * @return
     */
    public static String userQueryUsername(){
        return "user_query:idx:username";
    }

    /**
     * 含有价格的以zset存
     * 其中的key为id score为价格
     * @return
     */
    public static String userQueryPrice(){
        return "user_query:idx:price";
    }

    /**
     * 其中时间以zset存
     * 其中的key为id score为时间戳
     * @return
     */
    public static String userQueryUtime(){
        return "user_query:idx:utime";
    }

    /**
     * user_query本身的完整数据
     * @param id
     * @return
     */
    public static String userQueryData(Long id){
        return "user_query:"+id;
    }
    public static String userQueryData(String id){
        return "user_query:"+id;
    }


    /**
     * 这个是用户个人搜索的缓存信息，可以考虑加上用户的uid
     * @param username
     * @return
     */
    public static String userQueryUsernameSearch(String username){
        return "user_query:username:"+username;
    }

    /**
     * 这个
     * @param address
     * @return
     */
    public static String userQueryAddressSearch(String address){
        return "user_query:address:"+address;
    }

    /**
     * 这个
     * @param address
     * @return
     */
    public static String userQueryPriceSearch(Long startPrice,Long endPrice){
        return "user_query:price:"+startPrice+"/"+endPrice;
    }
    /**
     * 这个
     * @param address
     * @return
     */
    public static String userQueryUtimeSearch(Long startTime,Long endTime){
        return "user_query:time:"+startTime+"/"+endTime;
    }

    /**
     * 这个
     * @param address
     * @return
     */
    public static String userQueryAllSearch(String username,String address,Long startPrice,Long endPrice,Long startTime,Long endTime){
        return "user_query:complex:"+username+"/"+address+"/"+startPrice+"/"+endPrice+"/"+startTime+"/"+endTime;
    }


}
