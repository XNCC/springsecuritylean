package com.vbqncc.secondspringsecurity.secondspringsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author ncc
 * @site
 * @company
 * @create 2020-11-14 0:04
 */
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordEncoder PasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/mylogin")
                .defaultSuccessUrl("/success")//默认登陆成功的跳转页面
                .failureUrl("/error");  //失败页面
        //授权
        http.authorizeRequests()
                .antMatchers("/admin/api/**").hasRole("ADMIN")
                .antMatchers("/user/api/**").hasRole("USER")
                .antMatchers("/app/api/**").permitAll()
                .antMatchers("/static/page/**").permitAll()
                .antMatchers("/login").permitAll()//开放登陆页面需要的url
                .antMatchers("/error").permitAll()//错误页
                .anyRequest().authenticated();
        http.csrf().disable();
    }

    //使用内存方式认证
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("user").password(PasswordEncoder.encode("123")).roles("USER").build());
        userDetailsManager.createUser(User.withUsername("admin").password(PasswordEncoder.encode("123")).roles("ADMIN").build());
        return userDetailsManager;
    }

}
