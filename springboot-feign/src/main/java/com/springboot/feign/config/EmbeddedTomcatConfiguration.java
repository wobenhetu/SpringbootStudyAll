package com.springboot.feign.config;

import org.apache.catalina.connector.Connector;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/*
* 支持监听多个端口
*
* */

@Configuration
public class EmbeddedTomcatConfiguration {
 
    @Value("${websocket.port}")
    private String websocketPort;
 
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        Connector[] additionalConnectors = this.additionalConnector();
        if (additionalConnectors != null && additionalConnectors.length > 0) {
            tomcat.addAdditionalTomcatConnectors(additionalConnectors);
        }
        return tomcat;
    }
 
    private Connector[] additionalConnector() {
        if (StringUtils.isBlank(this.websocketPort)) {
            return null;
        }
        String[] ports = this.websocketPort.split(",");
        List<Connector> result = new ArrayList<>();
        for (String port : ports) {
            Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
            connector.setScheme("http");
            connector.setPort(Integer.valueOf(port));
            result.add(connector);
        }
        return result.toArray(new Connector[] {});
    }
}