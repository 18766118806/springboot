package com.pingan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

@SpringBootApplication
public class  SpringbootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run (SpringbootMybatisApplication.class, args);
    }
}