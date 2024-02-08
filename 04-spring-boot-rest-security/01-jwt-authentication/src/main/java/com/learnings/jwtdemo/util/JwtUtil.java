package com.learnings.jwtdemo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

//    @Value("${jwt.expirationMs}")
//    private long expirationMs;

    // Generate Token
    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // read subject/username
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // read exp date
    public Date getExpDate(String token) {
        return getClaims(token).getExpiration();
    }

    // Read Claims
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    // validate exp date
    public boolean isTokenExp(String token) {
        Date expDate = getExpDate(token);
        return expDate.before(new Date(System.currentTimeMillis()));
    }

    // validate user name is token and database, expDate
    public boolean validateToken(String token, String username) {
        String tokenUserName = getUsername(token);
        return (username.equals(tokenUserName) && !isTokenExp(token));


//        try {
//            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
    }
}
