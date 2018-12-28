package com.example.cloudstream1;

import org.jboss.logging.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Processor.class)
public class MyResource {
    @Autowired
    Processor sink;

    @GetMapping("/test")
    public String test(){
        sink.input()
                .send(MessageBuilder.withPayload(new Event1("val1","val2"))
                        .build());

        return "ok";
    }
}
