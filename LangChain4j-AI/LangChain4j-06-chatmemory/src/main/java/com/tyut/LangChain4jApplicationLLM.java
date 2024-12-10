package com.tyut;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@Slf4j
@SpringBootApplication
public class LangChain4jApplicationLLM {
    public static void main(String[] args) {
        SpringApplication.run(LangChain4jApplicationLLM.class, args);
        log.info("******************* 项目启动成功 *******************");
    }
}
