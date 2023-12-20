package com.contentdeliverynetwork.cdn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.apache.coyote.http2.Http2Protocol;

@SpringBootApplication
public class CdnApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdnApplication.class, args);
    }

}
