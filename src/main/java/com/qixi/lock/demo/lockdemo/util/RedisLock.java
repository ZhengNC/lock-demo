package com.qixi.lock.demo.lockdemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁Redis工具类
 * @author ZhengNC
 * @date 2020/5/13 17:27
 */
@Component
public class RedisLock {

    private static String keyPrefix = "testLockKey";

    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate){
        RedisLock.redisTemplate = redisTemplate;
    }

    /**
     * 获取锁
     * @param resource 要加锁的资源
     * @param identity 身份标识（保证锁不会被其他人释放）
     * @param expireTime 锁的过期时间（单位：秒）
     * @return
     */
    public static boolean tryLock(String resource, String identity, long expireTime){
        boolean lockResult = redisTemplate.opsForValue().setIfAbsent(keyPrefix + ":" + resource, identity, expireTime, TimeUnit.SECONDS);
        return lockResult;
    }

    /**
     * 释放锁
     * @param resource 加锁的资源
     * @param identity 身份标识（保证锁不会被其他人释放）
     * @return
     */
    public static boolean unLock(String resource, String identity){
        String luaScript =
                "if " +
                "   redis.call('get', KEYS[1]) == ARGV[1] " +
                "then " +
                "   return redis.call('del', KEYS[1]) " +
                "else " +
                "   return 0 " +
                "end";
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Boolean.class);
        redisScript.setScriptText(luaScript);
        List<String> keys = new ArrayList<>();
        keys.add(keyPrefix + ":" +resource);
        boolean result = redisTemplate.execute(redisScript, keys, identity);
        return result;
    }
}
