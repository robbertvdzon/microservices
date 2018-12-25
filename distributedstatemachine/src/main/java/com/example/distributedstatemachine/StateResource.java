package com.example.distributedstatemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StateResource {
    private StateMachine<States, Events> stateMachine;

    @Autowired
    public StateResource(StateMachine<States, Events> stateMachine) {
        this.stateMachine = stateMachine;
        stateMachine.start();
    }

    @GetMapping(path = "state")
    public String getState() {

        String state = stateMachine.getState().toString();
        log.info("getState:"+state);
        return state;
    }

    @GetMapping(path = "coin")
    public String coin() {
        log.info("add coin, state="+stateMachine.getState());
        return ""+stateMachine.sendEvent(Events.COIN);
    }

    @GetMapping(path = "push")
    public String push() {
        log.info("push, state="+stateMachine.getState());
        return ""+stateMachine.sendEvent(Events.PUSH);
    }

}
