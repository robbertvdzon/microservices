package com.example.webapp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class WebApp2Application {

    public static void main(String[] args) {
        SpringApplication.run(WebApp2Application.class, args);
    }

    @GetMapping
    public String getIndex() throws UnknownHostException {
        return "Hello from " + java.net.InetAddress.getLocalHost().getHostName();
    }
}
