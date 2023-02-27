package com.jjunpro.reactive.web.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JWToken 전체적인 설정 정보
 * @author jjunpro
 * @since 2023/02/26 PM 15:33
 */
@Component
public class JWTUtil {

    @Value("${springbootjjwt.jjwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(this.initToken(token))
            .getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public String initToken(String token) {
        return token.replace("Bearer ", "");
    }
}