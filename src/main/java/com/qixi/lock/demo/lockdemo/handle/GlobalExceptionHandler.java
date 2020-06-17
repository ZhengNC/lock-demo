package com.qixi.lock.demo.lockdemo.handle;

import com.qixi.lock.demo.lockdemo.common.ResponseEntity;
import com.qixi.lock.demo.lockdemo.common.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 * @author ZhengNC
 * @date 2020/5/24 20:17
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理实体类参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.paramFailed(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 处理方法参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e){
        if (e.getConstraintViolations().size() > 0){
            return ResponseEntity.paramFailed(
                    ((ConstraintViolation)e.getConstraintViolations().toArray()[0])
                            .getMessageTemplate()
            );
        }
        return ResponseEntity.paramFailed();
    }

    /**
     * 处理SpringWeb参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        return ResponseEntity.paramFailed();
    }

    /**
     * 处理自定义响应类异常
     * @param e
     * @return
     */
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity handleResponseException(ResponseException e){
        log.error("{}", e.getStackTrace());
        return ResponseEntity.failed(e.getCode(), e.getMessage());
    }

    /**
     * 处理所有的运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException e){
        log.error("{}", e.getStackTrace());
        e.printStackTrace();
        return ResponseEntity.failed();
    }
}
