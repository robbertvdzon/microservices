package com.example.cloudstream1;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(MyStream.class)
public class MyHandler2 {
    @StreamListener(MyStream.OUTPUT)
    public void handle(Event2 event1) {
        System.out.println("Received event2: " + event1);
    }
}