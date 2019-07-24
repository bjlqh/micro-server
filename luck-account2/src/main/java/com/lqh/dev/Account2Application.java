package com.lqh.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class Account2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Account2Application.class, args);
        String[] profiles = context.getEnvironment().getActiveProfiles();
        for (String profile : profiles) {
            System.out.println("当前使用的profile是：" + profile);
        }
    }
}
