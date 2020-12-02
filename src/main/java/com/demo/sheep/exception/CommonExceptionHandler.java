package com.demo.sheep.exception;


import com.demo.sheep.constant.ResultCode;
import com.demo.sheep.pojo.view.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    //可以自定义要处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e){
        //打印异常
        log.error(e.getMessage(),e);
        return Result.failure(ResultCode.UNKNOWN_ERROR);
    }
}
