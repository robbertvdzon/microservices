package com.example.cloudstream2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Date;

@EnableBinding(QuestionStream.class)
@Slf4j
public class MyHandler1 {

    @StreamListener(QuestionStream.INPUT)
    @SendTo(QuestionStream.OUTPUT)
    public AnswerEvent handle(QuestionEvent questionEvent) {
        log.info("recieved question:" + questionEvent);
        return new AnswerEvent(questionEvent.getQuestionId(), "My answer on "+new Date());
    }

    @StreamListener(QuestionStream.INPUT2)
    public void handle2(QuestionEvent questionEvent) {
        log.info("recieved question (2):" + questionEvent);
    }

}
