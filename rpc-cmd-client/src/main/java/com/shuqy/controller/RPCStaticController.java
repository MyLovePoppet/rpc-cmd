package com.shuqy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RPCStaticController {
    @CrossOrigin
    //连接登录页面
    @GetMapping(value = "/connect")
    public String connectPage() {
        return "connect";
    }

    @CrossOrigin
    //主页面
    @GetMapping(value = "/")
    public String execPage() {
        return "index";
    }
}
