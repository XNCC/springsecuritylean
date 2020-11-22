package com.vbqncc.securitycaptcha.securitycaptcha.authenticationProvider;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author ncc
 * @site
 * @company
 * @create 2020-11-15 9:43
 */
@Component
public class captchaAuthenticationProvider extends DaoAuthenticationProvider {

    public captchaAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.setPasswordEncoder(passwordEncoder);
        this.setUserDetailsService(userDetailsService);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        //实现图形验证码效验逻辑
        myWebAuthenticationDetails details = (myWebAuthenticationDetails) usernamePasswordAuthenticationToken.getDetails();
        if (!details.isImageCodeIdRight()) {
            throw new VerifyError();
        }
        //调用父类方法完成密码验证
        super.additionalAuthenticationChecks(userDetails, usernamePasswordAuthenticationToken);
    }


}
