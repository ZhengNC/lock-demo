package com.qixi.lock.demo.lockdemo.controller;

import com.qixi.lock.demo.lockdemo.common.ResponseEntity;
import com.qixi.lock.demo.lockdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author ZhengNC
 * @date 2020/6/5 18:38
 */
@RestController
@RequestMapping("test")
@Validated
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 测试分布式锁
     *
     * @param source
     * @return
     */
    @GetMapping("testTryLock")
    public ResponseEntity<Boolean> testRTryLock(
            @RequestParam("source")
            @NotBlank(message = "参数不能为空")
                    String source){
        return ResponseEntity.success(testService.testTryLock(source));
    }
}
