package com.vbqncc.secondspringsecurity.secondspringsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户api
 *
 * @create 2020-11-14 23:15
 */
@RestController
public class userController {
    @RequestMapping("/user/api/hello")
    public String userapi() {
        return "user say hello";
    }
}
