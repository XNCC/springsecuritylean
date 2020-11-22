package com.vbqncc.securityjwt.securityjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SecurityjwtApplicationTests {

    /**
     * jwt加密
     */
    @Test
    public void contextLoads() {
        long start = System.currentTimeMillis();
        long end = start + 60 * 1000;
        JwtBuilder jwt = Jwts.builder()
                //声明标识
                .setId("ncc")
                //声明主体，即用户
                .setSubject("jack")
                //设置创建日期
                .setIssuedAt(new java.util.Date(start))
                .setExpiration(new java.util.Date(end))
                .signWith(SignatureAlgorithm.HS256, "xxxx")
                .claim("role", "admin")
                .claim("lege", "xx.jpg");
        String token = jwt.compact();
        System.out.println(token);
    }

    /**
     * jwt解密
     */
    @Test
    public void test2() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJuY2MiLCJzdWIiOiJqYWNrIiwiaWF0IjoxNjA1NTI1MTM1LCJleHAiOjE2MDU1MjUxOTUsInJvbGUiOiJhZG1pbiIsImxlZ2UiOiJ4eC5qcGcifQ.QUibBqLVTyCbq84Oa50X-f5OJ4cYJbqG0TUO1jSsIr4";
        Claims claims = Jwts.parser()
                .setSigningKey("xxxx")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
        System.out.println(claims.get("role"));
        System.out.println(claims.get("lege"));

    }

}
