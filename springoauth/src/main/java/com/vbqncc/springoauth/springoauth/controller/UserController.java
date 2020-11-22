package com.vbqncc.springoauth.springoauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ncc
 * @site
 * @company
 * @create 2020-11-21 14:47
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 获取当前y用户
     * @param authentication
     * @return
     */
    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }
}
