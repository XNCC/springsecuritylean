package com.vbqncc.securitycaptcha.securitycaptcha.authenticationProvider;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ncc
 * @site
 * @company
 * @create 2020-11-15 11:56
 */
public class myWebAuthenticationDetails extends WebAuthenticationDetails {
    boolean imageCodeIdRight;

    public boolean isImageCodeIdRight() {
        return imageCodeIdRight;
    }

    public void setImageCodeIdRight(boolean imageCodeIdRight) {
        this.imageCodeIdRight = imageCodeIdRight;
    }

    public myWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        //认证逻辑
        String captcha = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("captcha");
        if (!StringUtils.isEmpty(code)) {
            session.removeAttribute("captcha");
            if (!StringUtils.isEmpty(code) && captcha.equals(code)) {
                this.imageCodeIdRight = true;
            }
        }
    }
}
