package com.craffic.yokowu.yokowu.hr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/hello")
    public String hello(){
        return "hello Oauth2";
    }

    @RequestMapping("/success")
    public String success(){
        return "login success";
    }

    @RequestMapping("/failure")
    public String failure(){
        return "login failure";
    }

}
