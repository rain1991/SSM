package com.rain.ssm.utils;

/**
 * 常量类
 */
public class Constants {
	/**
	 * 用户token
	 */
    public static final String APP_TOKEN = "appToken";
    /**
     * 数据流
     */
    public static final String REQUEST_STREM = "requestStream";
    /**
     * redis中token
     */
    public static final String REDIS_TOKEN_CACHE_NAME = "cvinfo_0";
    /**
	 * 默认缓存数据前缀
	 */
    public static final String PREFIX_SHIRO_REDIS_SESSION = "shiro_redis_session:";
    /**
	 * Redis key前缀
	 */
    public static final String PREFIX_SHIRO_REDIS_CACHE = "shiro_redis_cache:";
    /**
     * 请求成功
     */
    public static final String ERR_CODE_0 = "0";
    /**
     * 认证：未登录或登录超时
     */
    public static final String ERR_CODE_100 = "100";
    /**
     * 认证：账号或密码错误
     */
    public static final String ERR_CODE_101 = "101";
    /**
     * 授权：访问未授权
     */
    public static final String ERR_CODE_103 = "103";
    /**
     * 输入参数错误
     */
    public static final String ERR_CODE_400 = "400";
    /**
     * 系统内部错误
     */
    public static final String ERR_CODE_500 = "500";
    /**
     * 未知错误
     */
    public static final String ERR_CODE_999 = "999";
    /**
     * 数据库操作失败
     */
    public static final String ERR_CODE_600 = "600";
    /**
     * 数据为空
     */
    public static final String ERR_CODE_700 = "700";
    /**
     * 下单员的角色id
     */
    public static final int PLACE_ORDER_USER_ID = 111;
}
