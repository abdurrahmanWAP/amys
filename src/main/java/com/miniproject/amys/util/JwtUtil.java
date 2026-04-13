package com.miniproject.amys.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static String generateToken(String id, Timestamp timestamp){
        Map claims = new HashMap<String, Object>();
        claims.put("id",id);
        claims.put("exp", timestamp);

        var secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode("7193f1c919618c7fd7b181a4eb8eafed4b52b17abc13866b4ef18cf5a31c3f05"));
        var jwtBuilder = Jwts.builder().signWith(secretKey).setClaims(claims);

        return jwtBuilder.compact();
    }


    public static Claims validateToken(String token){
        var secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode("7193f1c919618c7fd7b181a4eb8eafed4b52b17abc13866b4ef18cf5a31c3f05"));
        try {
            var claims = Jwts.parserBuilder().setSigningKey(secretKey)
                    .build().parseClaimsJws(token).getBody();
            return claims;
        } catch (ExpiredJwtException ex){
            throw new RuntimeException("Token has expired");
        } catch (JwtException jx) {
            throw new RuntimeException("Token has invalid");
        }
    }
}
