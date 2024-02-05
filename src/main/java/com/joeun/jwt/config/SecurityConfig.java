package com.joeun.jwt.config;

//security 5.4 이하 extends websecuritconfigureradapter 상속받아했었음

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    //filterchain 5.5이상 bean등록해서 사용
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // 폼 기반 로그인 비활성화
        http.formLogin((login)-> login.disable()); //람다식 로그인객체를 가져와 비활성화
        
        // http 기본 인증 비활성화
        http.httpBasic((basic)->basic.disable());

        // csrf 공격 방어 기능 비활성화
        http.csrf((csrf)->csrf.disable());

        // 세션 관리 정책 설정
        // 세션 인증 사용안하고, jwt사용 인증, 세션 불필요
        http.sessionManagement((management)-> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
