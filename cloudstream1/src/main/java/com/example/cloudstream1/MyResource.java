package com.example.cloudstream1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(QuestionStream.class)
public class MyResource {
    @Autowired
    QuestionStream myStream;

    @GetMapping("/test")
    public String test(){
        myStream.outboundQuestion()
                .send(MessageBuilder.withPayload(new QuestionEvent("My Question")).build());
        return "question send";
    }

    @StreamListener(QuestionStream.INPUT)
    public void handle(AnswerEvent answer) {
        System.out.println("Got answer: " + answer);
    }
}
