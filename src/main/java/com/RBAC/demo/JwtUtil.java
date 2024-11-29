package com.RBAC.demo;

import io.jsonwebtoken.*;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "UFPD2aiVcKTF2baYvtPgMzgMlq049IrSi0OrmQe0BgpLOe0uibdUFxMLF8T6HHIl";
    private final long expiration  =  1800000 ; // 30 min

    public  String generateToken(String username , String role)
    {
        System.out.println(role);
        return  Jwts.builder()
                .setSubject(username)
                .claim("role" , role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS384 , secret)
                .compact();
    }
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

}
