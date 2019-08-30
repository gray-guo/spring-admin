package com.weizhang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.weizhang.dao")
public class WeizhangApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeizhangApplication.class, args);
    }

}
