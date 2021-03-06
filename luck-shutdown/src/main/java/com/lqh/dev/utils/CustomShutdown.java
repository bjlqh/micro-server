package com.lqh.dev.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.ProtocolHandler;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CustomShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final int TIMEOUT = 30;

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }


    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        ProtocolHandler protocolHandler = this.connector.getProtocolHandler();
        Executor executor = protocolHandler.getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                log.warn("WEB 应用即将关闭");
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();

                if (!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                    log.warn("WEB 应用等待关闭超过最大时长" + TIMEOUT + "秒，将进行强制关闭！");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("线程任务终止异常：{}", e.getMessage());
            }
        }
    }
}
