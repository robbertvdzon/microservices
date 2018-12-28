package com.example.cloudstream2;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(MyStream.class)
public class MyHandler2 {
    @StreamListener(MyStream.OUTPUT)
    public void handle(Event2 event1) {
        System.out.println("Received event2: " + event1);
    }
}
