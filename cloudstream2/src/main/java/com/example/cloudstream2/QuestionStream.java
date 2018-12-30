package com.example.cloudstream2;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface QuestionStream {
    String INPUT = "question-ask";
    String OUTPUT = "question-answer";

    @Input(INPUT)
    SubscribableChannel inboundQuestion();

    @Output(OUTPUT)
    MessageChannel outboundQuestion();
}