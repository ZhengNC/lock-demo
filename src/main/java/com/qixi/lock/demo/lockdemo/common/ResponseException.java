package com.qixi.lock.demo.lockdemo.common;

/**
 * 自定义响应异常类
 *
 * @author ZhengNC
 * @date 2020/5/25 15:51
 */
public class ResponseException extends RuntimeException {

    /**
     * 响应状态码，取值于{@link com.qixi.redlock.demo.common.constant.ResponseConstant}
     */
    private String code;

    public ResponseException(String message){
        super(message);
    }

    /**
     * @param code {@link com.qixi.redlock.demo.common.constant.ResponseConstant}
     * @param message
     */
    public ResponseException(String code, String message){
        super(message);
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
