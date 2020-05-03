package com.writingcode.www.community.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Chavy
 * @date 2020/4/13
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * jwt 签发key
     */
    private String jwtKey = "security";

    /**
     * jwt 签发人
     */
    private String jwtIssuer = "Chavy";
}
