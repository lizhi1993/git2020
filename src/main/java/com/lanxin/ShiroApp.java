package com.lanxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication                                  
@MapperScan(basePackages = "com.lanxin.dao")
public class ShiroApp {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApp.class,args);
    }
}
