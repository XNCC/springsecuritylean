package com.vbqncc.springoauthredisstore.springoauthredisstore.service;


import com.vbqncc.springoauthredisstore.springoauthredisstore.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author ncc
 * @site
 * @company
 * @create 2020-11-21 14:01
 */
@Service
public class MyUserDetilService implements UserDetailsService {
    @Autowired
    PasswordEncoder PasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String password = PasswordEncoder.encode("123456");
        return new User("admin", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return new User("admin", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
