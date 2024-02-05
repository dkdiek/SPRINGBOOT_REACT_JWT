package com.joeun.jwt.domain;

import lombok.Data;

@Data
public class AuthenticationReqeust {

    private String username;
    private String password;

}
