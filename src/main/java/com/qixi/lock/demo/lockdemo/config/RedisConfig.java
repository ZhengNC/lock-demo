package com.qixi.lock.demo.lockdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author ZhengNC
 * @date 2020/5/15 15:47
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean(name = "redisTemplate")
    public RedisTemplate getRedisTemplate(){
        return redisTemplate;
    }
}
