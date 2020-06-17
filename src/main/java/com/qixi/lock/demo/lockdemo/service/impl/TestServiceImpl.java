package com.qixi.lock.demo.lockdemo.service.impl;

import com.qixi.lock.demo.lockdemo.service.TestService;
import com.qixi.lock.demo.lockdemo.util.RedisLock;
import com.qixi.lock.demo.lockdemo.util.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 测试
 *
 * @author ZhengNC
 * @date 2020/6/5 9:55
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    /**
     * 测试分布式锁
     *
     * @param source
     * @return
     */
    @Override
    public Boolean testTryLock(String source) {
        String identity = Tools.randomStr(20, "lower");
        String threadId = Long.toString(Thread.currentThread().getId());
        identity = identity + ":" + threadId;
        log.info("尝试加锁:{}", source);
        Boolean result = RedisLock.tryLock(source, identity, 30);
        if (result){
            log.info("加锁成功:{}", source);
            //生成一个随机数，模拟业务需要处理的时间
            int time = Tools.randomNum(10, 20);
            log.info("执行业务, 处理:{}, 需要时间:{}秒", source, time);
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("业务执行完毕，释放锁");
            RedisLock.unLock(source, identity);
            log.info("释放锁成功，处理完毕:{}", source);
        }else {
            log.info("加锁失败:{}", source);
        }
        return result;
    }
}
