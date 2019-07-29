package com.lqh.dev;

import com.lqh.dev.utils.CustomShutdown;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ShutdownApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ShutdownApplication.class, args);
        String[] profiles = context.getEnvironment().getDefaultProfiles();
        for (String profile : profiles) {
            System.out.println("使用的profile是：" + profile);
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
        //tomcatEmbeddedServletContainerFactory.setProtocol("org.apache.coyote.http11.Http11NioProtocol");
        //tomcatEmbeddedServletContainerFactory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
        tomcatEmbeddedServletContainerFactory.addConnectorCustomizers(customShutdown);
        return tomcatEmbeddedServletContainerFactory;
    }
}
