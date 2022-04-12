package com.auth.service;

import java.util.Date;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Slf4j
@Service
public class JwtUtil {

    private static final String SECRET_KEY = "${secret.key}";

    public String extractUsername(String token) {

        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    public String generateToken(UserDetails userDetails) {
        try {

            return Jwts.builder().setSubject(userDetails.getPassword())
            .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() * 1000*60*30 ))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        }
        catch (ExpiredJwtException e)
        {
            System.out.println("Token Expired");
        }
        return null;
    }


    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;

        }
    }
}