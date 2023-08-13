package com.joker_yue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.joker_yue.mapper")
@SpringBootApplication
public class MybatisPlusQuickStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusQuickStartApplication.class, args);
    }

}
