package com.wqb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.wqb.mapper"})
public class WqbApplication {

    public static void main(String[] args) {
        SpringApplication.run(WqbApplication.class, args);
    }

}
