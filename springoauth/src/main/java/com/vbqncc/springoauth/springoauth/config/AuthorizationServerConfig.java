package com.vbqncc.springoauth.springoauth.config;

import com.vbqncc.springoauth.springoauth.service.MyUserDetilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * 认证服务器配置
 *
 * @create 2020-11-21 14:29
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //配置clientId
                .withClient("admin")
                //配置client-secret
                .secret(passwordEncoder.encode("112233"))
                //配置tocken过期时间
                .accessTokenValiditySeconds(3600)
                //重定向跳转
                .redirectUris("http://www.baidu.com")
                //配置申请权限范围
                .scopes("all")
                //配置granttype为授权码模式
                //.authorizedGrantTypes("authorization_code");
                .authorizedGrantTypes("password");

    }

    @Autowired
    AuthenticationManager AuthenticationManager;
    @Autowired
    MyUserDetilService MyUserDetilService;

    //密码模式
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.authenticationManager(AuthenticationManager).userDetailsService(MyUserDetilService);
    }
}
