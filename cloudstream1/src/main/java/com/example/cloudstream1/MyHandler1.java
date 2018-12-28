package com.example.cloudstream1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Processor.class)
public class MyHandler1 {

    @Autowired
    Processor processor;

    @StreamListener(Sink.INPUT)
    @SendTo(Processor.OUTPUT)
    public Event2 handle(Event1 event1) {
        System.out.println("Received event1: " + event1);
        return new Event2("val3", "val4");
    }
}
