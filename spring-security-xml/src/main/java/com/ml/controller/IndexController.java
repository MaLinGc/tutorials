package com.ml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/denied")
    public String denied() {
        return "denied";
    }

    @RequestMapping("/info")
    public String userInfo() {
        return "user_info";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/anonymous")
    public String anonymous() {
        return "anonymous";
    }

}
