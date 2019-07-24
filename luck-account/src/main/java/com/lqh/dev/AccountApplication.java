package com.lqh.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AccountApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AccountApplication.class, args);
        String[] profiles = context.getEnvironment().getDefaultProfiles();
        for (String profile : profiles) {
            System.out.println("使用的profile是：" + profile);
        }
    }
}
