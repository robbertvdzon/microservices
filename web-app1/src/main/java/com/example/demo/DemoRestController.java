package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@RestController
public class DemoRestController {

    @Autowired
    WebApp2Client webApp2Client;

    @GetMapping
    public String getIndex() throws UnknownHostException {
        try{
            return "Hello from " + java.net.InetAddress.getLocalHost().getHostName()+" and "+webApp2Client.hello();
        }
        catch (Exception ex){
            return "Hello from " + java.net.InetAddress.getLocalHost().getHostName()+". Calling WebApp2 via Feign does not work on Openshift since the hosts are not available by their hostname";
        }
    }

}
