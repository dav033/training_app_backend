package com.mpx90.training_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;


    public boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(final String token) {
        final Claims jwtToken = Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return jwtToken.getExpiration();
    }

    public SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        System.out.println("secretKey: " + secretKey);


        System.out.println("keyBytes: " + keyBytes);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        final Claims jwtToken = Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();


        return jwtToken.get("email", String.class);
    }
}