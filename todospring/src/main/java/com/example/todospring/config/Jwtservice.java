package com.example.todospring.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class Jwtservice  {

    private static  final String SECRET_KEY="GyROWBGtpA1pMadc/ReCx7ZIrGG0GLQpZsSqhsy0kLk=";

    public String extractusernames(String token){
        return extractclaim(token,Claims :: getSubject);
    }

    public <T> T extractclaim(String token, Function<Claims,T> cliamResolver){
        final Claims claims= extractAllClaims(token);
        return cliamResolver.apply(claims);
    }
    public String Generatetoken(UserDetails userDetails) {
        return generatetoken(new HashMap<>(), userDetails);
    }

    public String generatetoken(Map<String, Object> extractclaim, UserDetails userDetails) {
        // Adding a dynamic claim (optional)
        extractclaim.put("tokenId", UUID.randomUUID().toString());

        return Jwts.builder()
                .setClaims(extractclaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Expires in 24 hours
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isTokenvalid(String token,UserDetails userDetails){
        final String username=extractusernames(token);
        return (username.equals(userDetails.getUsername())) && !IsTOKENEXPIERED(token);
    }

    private boolean IsTOKENEXPIERED(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractclaim(token,Claims :: getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigninKey() {
        byte[] keybites = Decoders.BASE64.decode(SECRET_KEY );
        return Keys.hmacShaKeyFor(keybites);
    }
}
