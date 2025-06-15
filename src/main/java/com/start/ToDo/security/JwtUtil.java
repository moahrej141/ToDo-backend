//security/JwtUtil.java
package com.start.ToDo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.nio.charset.StandardCharsets;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "zLx7dHkZ4R9wW1pQ2uF7aSgV8xC3nMmTpJsYqKzE6vHbRtUgFxQeZiLw";
    private static final long EXPIRATION_TIME = 86400000; 
    public String generateToken(String email) {
        Key key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        Key key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
