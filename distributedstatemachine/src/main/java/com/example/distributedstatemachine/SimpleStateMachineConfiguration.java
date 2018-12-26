package com.example.distributedstatemachine;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.ensemble.StateMachineEnsemble;
import org.springframework.statemachine.zookeeper.ZookeeperStateMachineEnsemble;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
@Slf4j
public class SimpleStateMachineConfiguration
        extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Value("${zookeeperhost}")
    String zookeeperhost;

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> states)
            throws Exception {
        states
                .withDistributed()
                .ensemble(stateMachineEnsemble())
                .and()
                .withConfiguration()
                .autoStartup(true);
    }

    @Bean
    public StateMachineEnsemble<States, Events> stateMachineEnsemble() throws Exception {
        return new ZookeeperStateMachineEnsemble<States, Events>(curatorClient(), "/foo");
    }

    @Bean
    public CuratorFramework curatorClient() throws Exception {
        log.info("Remote zookeeper:" + zookeeperhost);
        CuratorFramework client = CuratorFrameworkFactory.builder().defaultData(new byte[0])
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .connectString(zookeeperhost + ":2181").build();
        client.start();
        return client;
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.LOCKED)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.LOCKED)
                .target(States.UNLOCKED)
                .event(Events.COIN)
                .and()
                .withExternal()
                .source(States.UNLOCKED)
                .target(States.LOCKED)
                .event(Events.PUSH);
    }

}