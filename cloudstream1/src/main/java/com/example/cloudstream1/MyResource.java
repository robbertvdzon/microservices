package com.example.cloudstream1;

import org.reactivestreams.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@EnableBinding(QuestionStream.class)
public class MyResource {
    Map<String, MonoProcessor<String>> completableFutures = new HashMap<>();

    @Autowired
    QuestionStream myStream;

    @GetMapping("/test")
    public Mono<String> test(){
        String uuid = UUID.randomUUID().toString();

        Mono<String> defer = Mono.from(createFuture(uuid));
        myStream.outboundQuestion().send(MessageBuilder.withPayload(new QuestionEvent(uuid, "My Question")).build());
        return defer;
    }

    @StreamListener(QuestionStream.INPUT)
    public void handle(AnswerEvent answer) {
        completableFutures.get(answer.getQuestionId()).onNext(answer.getAnswer());
        System.out.println("Got answer: " + answer);
    }

    private Processor<String, String> createFuture(String uuid) {
        MonoProcessor<String> future = MonoProcessor.create();
        completableFutures.put(uuid, future);
        return future;
    }


}
