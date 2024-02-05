package com.joeun.jwt.controller;

import com.joeun.jwt.constants.SecurityConstants;
import com.joeun.jwt.domain.AuthenticationReqeust;
import com.joeun.jwt.prop.JwtProp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private JwtProp jwtProp;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationReqeust reqeust){
        String username = reqeust.getUsername();
        String password = reqeust.getPassword();

        log.info("username: " + username);
        log.info("password: " + password);

        //사용자 권한
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");
        
        //시크릿키 ->바이트
        byte[] signinKey = jwtProp.getSecretKey().getBytes();
        
        //토큰 생성
        String jwt = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signinKey) , Jwts.SIG.HS512 ) //시크릿키,알고리즘
                .header()
                    .add("typ", SecurityConstants.TOKEN_TYPE)
                .and()
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*24*5))
                .claim("uid",username)
                .claim("rol",roles)
                .compact();
        
        log.info("jwt: "+ jwt);

        return new ResponseEntity<String>(jwt, HttpStatus.OK);

    }

}
