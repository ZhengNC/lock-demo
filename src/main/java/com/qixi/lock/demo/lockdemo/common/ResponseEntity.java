package com.qixi.lock.demo.lockdemo.common;

import com.qixi.lock.demo.lockdemo.common.constant.ResponseConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一响应实体
 *
 * @author ZhengNC
 * @date 2020/5/24 12:26
 */
@ApiModel("统一响应实体")
@Data
public class ResponseEntity<T> {

    @ApiModelProperty("响应状态码")
    private String code;

    @ApiModelProperty("响应信息")
    private String msg;

    @ApiModelProperty("业务体")
    private T data;

    public ResponseEntity() {}

    public ResponseEntity(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功的响应
     * @param data
     * @return
     */
    public static ResponseEntity success(Object data){
        return new ResponseEntity(ResponseConstant.SUCCESS.code,
                ResponseConstant.SUCCESS.msg, data);
    }

    /**
     * 服务器错误的消息响应
     * @return
     */
    public static ResponseEntity failed(){
        return new ResponseEntity(ResponseConstant.FAILED.code,
                ResponseConstant.FAILED.msg, null);
    }

    /**
     * 自定义服务器错误的消息响应
     * @param code
     * @param msg
     * @return
     */
    public static ResponseEntity failed(String code, String msg){
        return new ResponseEntity(code,msg, null);
    }

    /**
     * 默认的参数错误响应
     * @return
     */
    public static ResponseEntity paramFailed(){
        return new ResponseEntity(ResponseConstant.PARAM_FAILED.code,
                ResponseConstant.PARAM_FAILED.msg, null);
    }

    /**
     * 自定义消息的参数错误响应
     * @param message
     * @return
     */
    public static ResponseEntity paramFailed(String message){
        return new ResponseEntity(ResponseConstant.PARAM_FAILED.code,
                message, null);
    }
}
