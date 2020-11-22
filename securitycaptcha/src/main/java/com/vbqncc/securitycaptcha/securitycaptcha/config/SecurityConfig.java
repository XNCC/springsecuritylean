package com.vbqncc.securitycaptcha.securitycaptcha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ncc
 * @site
 * @company
 * @create 2020-11-15 10:53
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    AuthenticationProvider authenticationProvider;
//
//    //    AuthenticationProvider交给AuthenticationManager管理
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider);
//    }

    @Autowired
    AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> myAuthenticationDetailsSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .authenticationDetailsSource(myAuthenticationDetailsSource)
                .loginPage("/login")
                .loginProcessingUrl("/mylogin")
                .defaultSuccessUrl("/success")//默认登陆成功的跳转页面
                .failureUrl("/error");  //失败页面
        //授权
        http.authorizeRequests()
                .antMatchers("/login").permitAll()//开放登陆页面需要的url
                .antMatchers("/error").permitAll()//错误页
                .antMatchers("/captch.jpg").permitAll()//错误页
                .anyRequest().authenticated();
        http.csrf().disable();
    }

    //使用内存方式认证
    @Autowired
    PasswordEncoder PasswordEncoder;

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("user").password(PasswordEncoder.encode("123")).roles("USER").build());
        userDetailsManager.createUser(User.withUsername("admin").password(PasswordEncoder.encode("123")).roles("ADMIN").build());
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
