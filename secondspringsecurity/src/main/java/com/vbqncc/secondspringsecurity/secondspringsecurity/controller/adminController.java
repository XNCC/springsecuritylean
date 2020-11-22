package com.vbqncc.secondspringsecurity.secondspringsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员api
 *
 * @create 2020-11-14 23:11
 */
@RestController
public class adminController {

    @RequestMapping("/admin/api/hello")
    public String adminapi() {
        return "admin say hello";
    }
}
