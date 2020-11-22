package com.vbqncc.secondspringsecurity.secondspringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ncc
 * @site
 * @company
 * @create 2020-11-13 14:03
 */
@Controller
public class myController {

    @GetMapping("/login")//登陆页面
    public String mylogin() {
        return "/page/login.html";
    }

    @GetMapping("/success")//登陆成功
    public String mypage() {
        return "/page/success.html";
    }

    @GetMapping("/error")//登陆失败
    public String toError() {
        return "/page/error.html";
    }
}
