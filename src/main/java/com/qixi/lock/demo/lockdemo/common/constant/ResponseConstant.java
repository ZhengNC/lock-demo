package com.qixi.lock.demo.lockdemo.common.constant;

/**
 * @author ZhengNC
 * @date 2020/5/24 12:33
 */
public enum ResponseConstant {
    SUCCESS("200", "请求成功"),
    PARAM_FAILED("400", "参数错误"),
    UNAUTHORIZED("401", "权限不足"),
    NOT_FOUND("404", "找不到资源"),
    EXISTED("460", "资源已存在"),
    FAILED("500", "请求失败");

    public final String code;
    public final String msg;

    ResponseConstant(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
