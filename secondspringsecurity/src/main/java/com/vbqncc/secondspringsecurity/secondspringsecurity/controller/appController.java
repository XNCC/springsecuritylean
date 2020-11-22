package com.vbqncc.secondspringsecurity.secondspringsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * app客户端api
 *
 * @create 2020-11-14 23:13
 */
@RestController
public class appController {

    @RequestMapping("/app/api/hello")
    public String appapi() {
        return "app say hello";
    }
}
