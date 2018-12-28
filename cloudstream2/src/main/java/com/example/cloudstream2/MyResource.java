package com.example.cloudstream2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(MyStream.class)
public class MyResource {
    @Autowired
    MyStream sink;

    @GetMapping("/test")
    public String test(){
        sink.inboundGreetings()
                .send(MessageBuilder.withPayload(new Event1("val1","val2"))
                        .build());

        return "ok";
    }
}
