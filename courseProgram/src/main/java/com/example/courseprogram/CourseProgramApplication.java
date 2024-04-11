package com.example.courseprogram;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.courseprogram.repository")
public class CourseProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseProgramApplication.class, args);
    }

}
