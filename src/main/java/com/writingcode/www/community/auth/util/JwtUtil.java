package com.writingcode.www.community.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.writingcode.www.community.auth.properties.JwtProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Chavy
 * @date 2020/4/13
 */
@Component
@AllArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class JwtUtil {

    private JwtProperties jwtProperties;


    public String createJwt(String subject, long expire) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer(jwtProperties.getJwtIssuer())
                    .withSubject(subject)
                    .withExpiresAt(new Date(System.currentTimeMillis() + expire))
                    .sign(Algorithm.HMAC256(jwtProperties.getJwtKey()));
        } catch (JWTCreationException exception) {
            return null;
        }
        return token;
    }

    public String getSubject(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtProperties.getJwtKey()))
                    .withIssuer(jwtProperties.getJwtIssuer())
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
