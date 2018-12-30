package com.example.cloudstream1;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface QuestionStream {
    String INPUT = "question-answer";
    String OUTPUT = "question-ask";

    @Input(INPUT)
    SubscribableChannel inboundQuestion();

    @Output(OUTPUT)
    MessageChannel outboundQuestion();
}