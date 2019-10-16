package com.lxy.gmall.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Chris
 * @data 2019-10-14 下午 3:49
 */
public class RedisUtil {
    //获取Jedis
    private JedisPool jedisPool;

    public void initJedisPool(String host, int port, int timeout) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(200);
        jedisPoolConfig.setMaxWaitMillis(10*1000);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setBlockWhenExhausted(true);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }
}
