package com.lanxin.error;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//拦截controller
public class MyError{

    @ExceptionHandler(value = UnauthorizedException.class)//捕捉所有异常
    @ResponseBody//返回json数据
    public LanxinResult  defaulterror(){

             return new LanxinResult(600,"您的权限不足",null);
    }
}
