package com.vbqncc.firstspringsecurity.firstspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * @author ncc
 * @site
 * @Desc 基于数据的认证
 * @create 2020-11-12 23:23
 */
@Service
public class MyUserDetailsServic implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;//一种单项加密手段，只能加密不能解密

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里假设一个从数据库查询到得密码123456，然后拿着这个密码跟前台传过来得密码进行对比。
        JdbcUserDetailsManager userDetailsManager=new JdbcUserDetailsManager();
//        String usersByUsernameQuery = userDetailsManager.getUsersByUsernameQuery();
        String password = passwordEncoder.encode("123456");
//        AuthorityUtils.commaSeparatedStringToAuthorityList("admin")是springsecurity提供的分割“；”的工具
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
