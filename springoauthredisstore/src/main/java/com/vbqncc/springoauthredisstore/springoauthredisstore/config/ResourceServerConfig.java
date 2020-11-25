package com.vbqncc.springoauthredisstore.springoauthredisstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器配置
 *
 * @create 2020-11-21 14:40
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    //相应资源放行操作
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //所有资源都需要认证
                .anyRequest()
                .authenticated()
                .and()
                //但是放行/user/**下的数据
                .requestMatchers()
                .antMatchers("/user/**");
    }
}
