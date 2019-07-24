package com.zouxxyy.blog.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.zouxxyy.blog.core.dao")
@SpringBootApplication
public class ZxyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZxyBlogApplication.class, args);
    }

}
