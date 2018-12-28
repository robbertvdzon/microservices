package com.example.cloudstream2;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(MyStream.class)
public class MyHandler1 {

//    @Autowired
//    MyStream processor;

    @StreamListener(MyStream.INPUT)
    @SendTo(MyStream.OUTPUT)
    public Event2 handle(Event1 event1) {
        System.out.println("Received event1: " + event1);
        return new Event2("val3");
    }
}
