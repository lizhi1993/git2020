package com.lanxin.controller;

import com.lanxin.error.Error;
import com.lanxin.error.LanxinResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {

    @RequestMapping("/login")
    public LanxinResult login(String ename,String password){

        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken(ename,password);

        try {
            subject.login(token);

             return   LanxinResult.ok(null);

        } catch (IncorrectCredentialsException e){

            throw new Error(300,"密码错误!");

        } catch (AuthenticationException e) {

             throw new Error(600,"该用户不存在！");
        }catch (Exception e){

            throw  new Error(300,"登录失败！");
        }

    }

    @RequiresPermissions("select")
    @RequestMapping("/select")
    public LanxinResult select(){
        System.out.println("查询操作");
        return LanxinResult.ok(null);
    }

    @RequiresPermissions("update")
    @RequestMapping("/update")
    public LanxinResult update(){
        System.out.println("修改操作");
        return LanxinResult.ok(null);
    }

    @RequiresPermissions("add")
    @RequestMapping("/add")
    public LanxinResult add(){
        System.out.println("添加操作");
        return LanxinResult.ok(null);
    }

    @RequestMapping("/unauth")
    public LanxinResult unauth(){

        return new LanxinResult(100,"未登录",null);
    }
}
