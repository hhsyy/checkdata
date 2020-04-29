package com.yiyuclub.checkdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yiyuclub.checkdata.mapper")
public class CheckdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckdataApplication.class, args);
    }

}
