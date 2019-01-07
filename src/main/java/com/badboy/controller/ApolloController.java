package com.badboy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApolloController {


    @Value("${hello:apollo}")
    private String hello;

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String say() {
        return "hello: " + hello;
    }
}
