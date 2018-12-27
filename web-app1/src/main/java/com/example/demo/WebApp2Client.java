package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "WebApp2")
public interface WebApp2Client {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    String hello();
}
