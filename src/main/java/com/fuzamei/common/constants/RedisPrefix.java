package com.fuzamei.common.constants;

/**
 * @author Created by fuzamei on 2018/7/1.
 */
public class RedisPrefix {


    /**
     * 表示省，后面拼0，表示中国下面的省
     */
    public static final String PROVINCE = "PROVINCES:";

    /**
     * 表示市，后面拼接省的id号表示省下面的城市
     */
    public static final String CITY = "CITIES:";

    /**
     * 表示区，后面拼接城市的id号，表示城市下面的区
     */
    public static final String DISTRICT = "DISTRICTS:";

    /**
     * 后台用户token的redis前缀
     */
    public static final String USER_TOKEN_PREFIX = "USER_TOKEN:";
    /**
     * 后台用户token的redis的过期时间(暂时设置60分钟)
     */
    public static final long USER_TOKEN_TIMEOUT = 60L * 60 * 1000;

    /**
     * 后台用户权限校验时用户详细信息的redis的key值前缀
     */
    public static final String USER_AUTH_PREFIX = "USER_AUTHORITY:";
    /**
     * 后台用户权限校验时用户详细信息的redis的key值前缀
     */
    public static final long USER_AUTH_TIMEOUT = -1L;
    /**
     * 个人认证信息的redis的key值前缀
     */
    public static final String PERSON_INFO = "PERSON_INFO:";
    /**
     * 企业认证信息的redis的key值前缀
     */
    public static final String ENTERPRISE_INFO = "ENTERPRISE_INFO:";
    /**
     * 律师认证信息的redis的key值前缀
     */
    public static final String LAWYER_INFO = "LAWYER_INFO:";
    /**
     * 金融主体认证信息的redis的key值前缀
     */
    public static final String FINANCIAL_INFO = "FINANCIAL_INFO:";
    /**
     * 企业或金融主体员工认证信息的redis的key值前缀
     */
    public static final String STAFF_INFO = "STAFF_INFO:";

    /**
     * 手机验证redis前缀
     */
    public static final String CODE_VERIFY_PHONE = "CODE_VERIFY_PHONE:";

    /**
     * 邮箱验证redis前缀
     */
    public static final String CODE_VERIFY_EMAIL = "CODE_VERIFY_EMAIL:";

    /**
     * 手机验证超时时间
     */
    public static final long CODE_VERIFY_PHONE_TIMEOUT = 300000L;

    /**
     * 合同主键ID，加锁用
     */
    public static final String CONTRACT_ID = "CONTRACT_ID:";

    /**
     * 省市区所有的id和名字的hash的key值
     */
    public static final String CN_AREA = "CN_AREA";

    /**
     * 登录时候检查账号的hash的key值，hash保存有account和password
     */
    public static final String LOGIN_ACCOUNT = "LOGIN_ACCOUNT";



    //===================================后台认证信息分页查询↓↓↓↓↓↓↓↓↓↓↓↓==============================================
    /**
     * 以下都是后台认证信息分页查询的key值前缀
     */
    public static final String PERSON_AUTHENTICATION_CACHE = "PERSON_AUTHENTICATION_CACHE";
    public static final String LAWYER_AUTHENTICATION_CACHE = "LAWYER_AUTHENTICATION_CACHE";
    public static final String ENTERPRISE_AUTHENTICATION_APPLY_CACHE = "ENTERPRISE_AUTHENTICATION_APPLY_CACHE";
    public static final String FINANCIAL_AUTHENTICATION_APPLY_CACHE = "FINANCIAL_AUTHENTICATION_APPLY_CACHE";

    /**
     * 与上面的分页查询联合使用，后面跟的id是该认证信息的id号，而不是uid
     */
    public static final String PERSON_AUTHENTICATION = "PERSON_AUTHENTICATION:";
    public static final String LAWYER_AUTHENTICATION = "LAWYER_AUTHENTICATION:";
    public static final String ENTERPRISE_AUTHENTICATION_APPLY = "ENTERPRISE_AUTHENTICATION_APPLY:";
    public static final String FINANCIAL_AUTHENTICATION_APPLY = "FINANCIAL_AUTHENTICATION_APPLY:";
    //===================================后台认证信息分页查询↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑==============================================






    //==========================加锁用===========================

    /**
     * 企业账号创建子账号的锁key
     */
    public static final String CREATE_STAFF = "CREATE_STAFF:";

    /**
     * 企业账号冻结启用子账号的锁key
     */
    public static final String START_STOP_STAFF = "START_STOP_STAFF:";




    /**
     * 锁的统一value
     */
    public static final String LOCK = "lock";
    /**
     * 锁的统一过期时间，单位是s(秒)
     */
    public static final long LOCK_EXPIRE_TIME = 5L * 60;




}
