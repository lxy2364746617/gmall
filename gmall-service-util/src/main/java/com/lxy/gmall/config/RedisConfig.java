package com.lxy.gmall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chris
 * @data 2019-10-14 下午 3:58
 */
@Configuration
public class RedisConfig {

    //获取host,port,timeout等参数
    @Value("${spring.redis.host:disable}")//disable:表示如果没有获取到host，则给默认值
    private String host;

    @Value("${spring.redis.port:0}")
    private int port;

    @Value("${spring.redis.timeout:10000}")
    private int timeout;

    @Bean
    public RedisUtil getRedisUtil() {
        if("disabled".equals(host)) {
            return null;
        }
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.initJedisPool(host, port, timeout);
        return redisUtil;
    }
}
