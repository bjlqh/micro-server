package com.lqh.dev;

import com.lqh.dev.utils.CustomShutdown;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class Account2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Account2Application.class, args);
        String[] profiles = context.getEnvironment().getDefaultProfiles();
        for (String profile : profiles) {
            System.out.println("当前使用的profile是：" + profile);
        }
    }

    @Bean
    public CustomShutdown customShutdown() {
        return new CustomShutdown();
    }

    @Bean
    public TomcatEmbeddedServletContainerFactory getContainerCustomizer(final CustomShutdown customShutdown) {
        TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory =
                new TomcatEmbeddedServletContainerFactory();
        tomcatEmbeddedServletContainerFactory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
        tomcatEmbeddedServletContainerFactory.addConnectorCustomizers(customShutdown);
        return tomcatEmbeddedServletContainerFactory;
    }
}
