package com.demo.sheep.pojo.view;


import com.demo.sheep.constant.ResultCode;

import java.io.Serializable;

/**
 * 请求返回的对象
 */
public class Result implements Serializable {
    private int code;
    private String enMsg;
    private String msg;
    private Object data;

    public Result() {
    }

    public Result(int code, String enMsg, String msg, Object data) {
        this.code = code;
        this.enMsg = enMsg;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEnMsg() {
        return enMsg;
    }

    public void setEnMsg(String enMsg) {
        this.enMsg = enMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.enMsg = resultCode.enMessage();
        this.msg = resultCode.zhMessage();
    }


    public static Result success(Object data){
        Result result =  new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }
    public static Result success(){
        Result result =  new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }
    public static Result failure(ResultCode resultCode){
        Result result =  new Result();
        result.setResultCode(resultCode);
        return result;
    }
    public static Result failure(ResultCode resultCode, Object data){
        Result result =  new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
}
