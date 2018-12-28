package com.example.cloudstream1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
public class Cloudstream1Application {

    public static void main(String[] args) {
        SpringApplication.run(Cloudstream1Application.class, args);
    }


}

