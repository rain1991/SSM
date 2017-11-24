package com.rain.ssm.utils;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtils {
	
	private static Logger logger = Logger.getLogger(RedisUtils.class);
	
	/** 默认缓存时间 */
	private static final int DEFAULT_CACHE_SECONDS = 60 * 60 * 1;// 单位秒 设置成一个小时
	
	/** 连接池 **/
	private static JedisPool jedisPool;
		
	public RedisUtils(JedisPool jedisPool) {
		RedisUtils.jedisPool = jedisPool;
	}
	public static JedisPool getJedisPool() {
		return jedisPool;
	}
	public static void setJedisPool(JedisPool jedisPool) {
		RedisUtils.jedisPool = jedisPool;
	}

	/**
	 * 释放redis资源
	 * 
	 * @param jedis
	 */
	private static void releaseResource(Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}
	
	//保存一个对象到Redis中(缓存过期时间:使用此工具类中的默认时间)
	public static Boolean save(Object key, Object object) {
		return save(key, object, DEFAULT_CACHE_SECONDS);
	}
	
	
	//保存一个对象到redis中并指定过期时间
	public static Boolean save(Object key, Object object, int seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(SerializeUtils.serialize(key), SerializeUtils.serialize(object));
			jedis.expire(SerializeUtils.serialize(key), seconds);
			return true;
		} catch (Exception e) {
			logger.error("Redis保存失败：" + e);
			e.printStackTrace();
			return false;
		} finally {
			releaseResource(jedis);
		}
	}
	
	//根据键获取Redis缓存中的值
	public static Object get(Object key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if(SerializeUtils.serialize(key)==null){
				return null;
			}
			byte[] obj = jedis.get(SerializeUtils.serialize(key));
			return obj == null ? null : SerializeUtils.deserialize(obj);
		} catch (Exception e) {
			logger.error("Redis获取失败：" + e);
			e.printStackTrace();
			return null;
		} finally {
			releaseResource(jedis);
		}
	}
	
	//根据键清除Redis缓存中的值
	public static Boolean del(Object key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(SerializeUtils.serialize(key));
			return true;
		} catch (Exception e) {
			logger.error("Redis删除失败：" + e);
			e.printStackTrace();
			return false;
		} finally {
			releaseResource(jedis);
		}
	}
	
	//设置超时时间（单位为秒）
	public static Boolean expire(Object key, int seconds) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.expire(SerializeUtils.serialize(key), seconds);
			return true;
		} catch (Exception e) {
			logger.error("Redis设置超时时间失败：" + e);
			e.printStackTrace();
			return false;
		} finally {
			releaseResource(jedis);
		}
	}
	
	//判断一个key是否存在
	public static Boolean exists(Object key) {
		Jedis jedis = null;
		Boolean result = false;
		try {
			jedis = jedisPool.getResource();
			result = jedis.exists(SerializeUtils.serialize(key));
			return result;
		} catch (Exception e) {
			logger.error("Redis获取失败：" + e);
			e.printStackTrace();
			return false;
		} finally {
			releaseResource(jedis);
		}
	}

}
