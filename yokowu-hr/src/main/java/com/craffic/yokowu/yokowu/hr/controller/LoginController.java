package com.craffic.yokowu.yokowu.hr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/hello")
    public String hello(){
        return "hello Oauth2";
    }
}