package com.example.cloudstream1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@EnableBinding(QuestionStream.class)
public class MyResource {
    Map<String, CompletableFuture<String>> completableFutures = new HashMap<>();

    @Autowired
    QuestionStream myStream;

    @GetMapping("/test")
    public Mono<String> test(){
        String uuid = UUID.randomUUID().toString();
        Mono<String> defer = Mono.fromFuture(createFuture(uuid));
        myStream.outboundQuestion().send(MessageBuilder.withPayload(new QuestionEvent(uuid, "My Question")).build());
        return defer;
    }

    @StreamListener(QuestionStream.INPUT)
    public void handle(AnswerEvent answer) {
        completableFutures.get(answer.getQuestionId()).complete(answer.getAnswer());
        System.out.println("Got answer: " + answer);
    }

    private CompletableFuture<String> createFuture(String uuid) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFutures.put(uuid, completableFuture);
        return completableFuture;
    }


}
