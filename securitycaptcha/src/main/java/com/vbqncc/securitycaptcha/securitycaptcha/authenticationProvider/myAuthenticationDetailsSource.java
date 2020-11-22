package com.vbqncc.securitycaptcha.securitycaptcha.authenticationProvider;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ncc
 * @site
 * @company
 * @create 2020-11-15 13:13
 */
@Component
public class myAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {

        return new myWebAuthenticationDetails(request);
    }
}
