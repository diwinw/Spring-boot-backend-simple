package com.miniBankend.comfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String subject) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token, String subject) {
        String tokenSubject = extractSubject(token);
        return tokenSubject.equals(subject) && !isTokenExpired(token);
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractExpirationDate(token);
        return expiration.before(new Date());
    }

    List<String> listToken = new ArrayList<>();
    @Cacheable(cacheNames ={"JWT"})
    public List<String> getToken(){
        return listToken;
    }

    @CachePut(cacheNames ={"JWT"})
    public List<String>putToken(String token){
        listToken.add(token) ;
        return listToken;

    }

//    @CachePut(cacheNames ={"JWT"})
//    public  Map<String,String> deleteToken(String token,String key){
//        listToken.get(token).remove(key);
//        return listToken.get(token);
//
//    }
}
