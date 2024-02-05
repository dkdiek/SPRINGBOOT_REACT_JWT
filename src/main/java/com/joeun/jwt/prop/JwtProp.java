package com.joeun.jwt.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("com.joeun.jwt")
public class JwtProp {
    
    //properties에 등록해놓은 시크릿키
    private String secretKey;


}
