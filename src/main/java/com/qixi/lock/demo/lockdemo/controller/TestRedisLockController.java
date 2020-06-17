package com.qixi.lock.demo.lockdemo.controller;

import com.qixi.lock.demo.lockdemo.common.ResponseEntity;
import com.qixi.lock.demo.lockdemo.util.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * 测试分布式锁
 * @author ZhengNC
 * @date 2020/5/13 17:27
 */
@RestController
@RequestMapping("testRedisLock")
@Validated
public class TestRedisLockController {

    @Autowired
    private RedisLock redisLock;

    /**
     * 测试加锁
     * @param source 加锁的资源
     * @param identity 身份标识
     * @return
     */
    @GetMapping("tryLock")
    public ResponseEntity<String> tryLock(
            @RequestParam("source") @NotBlank(message = "参数不能为空") String source,
            @RequestParam("identity") @NotBlank(message = "参数不能为空") String identity){
        boolean lockSuccess = redisLock.tryLock(source, identity, 60);
        String result = "lock failed";
        if (lockSuccess){
            result = "lock success";
        }
        return ResponseEntity.success(result);
    }

    /**
     * 测试释放锁
     * @param source 释放锁的资源
     * @param identity 身份标识
     * @return
     */
    @GetMapping("unLock")
    public ResponseEntity<String> unLock(
            @RequestParam("source") @NotBlank(message = "参数不能为空") String source,
            @RequestParam("identity") @NotBlank(message = "参数不能为空") String identity){
        boolean releaseSuccess = redisLock.unLock(source, identity);
        String result = "release failed";
        if (releaseSuccess){
            result = "release success";
        }
        return ResponseEntity.success(result);
    }
}
