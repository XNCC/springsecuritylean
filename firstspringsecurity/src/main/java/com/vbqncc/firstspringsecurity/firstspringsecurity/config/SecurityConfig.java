package com.vbqncc.firstspringsecurity.firstspringsecurity.config;

import com.vbqncc.firstspringsecurity.firstspringsecurity.service.MyUserDetailsServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ncc
 * @site
 * @company
 * @create 2020-11-12 23:30
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    MyUserDetailsServic userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * form表单
         */
        http.formLogin()
                //跳转岛登陆页
                .loginPage("/login")
                //处理登陆的url
                .loginProcessingUrl("/mylogin")
                //用于前后端分离的场景
//                .successHandler(new successHadler("http://www.baidu.com"))
//                .failureHandler(new failHandler("http://www.baidu.com"));
                .defaultSuccessUrl("/success")//默认登陆成功的跳转页面
                .failureUrl("/error")//失败页面
                .and()
                .rememberMe()   //对记住我进行设置
//                .tokenRepository(persistentTokenRepository())   持久化tocken导数据方式
                .tokenValiditySeconds(1000) //设置Token的有效时间
                .userDetailsService(userDetailsService)
                .and()
                .logout();


        /**
         * 授权
         */
        http.authorizeRequests()
                //开放登录页
                .antMatchers("/page/**").permitAll()
                .antMatchers("/login").permitAll()//开放登陆页面需要的url
                .antMatchers("/error").permitAll()//错误页
                .antMatchers("/js/**", "/img/**", "/css/**").permitAll() //开放静态资源
                //其他所有资源访问都需要登陆认证
                .anyRequest().authenticated();
        /**
         * 关闭csrf授权
         */
        http.csrf().disable();

    }
}

