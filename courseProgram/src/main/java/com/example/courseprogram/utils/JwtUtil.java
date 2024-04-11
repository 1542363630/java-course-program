package com.example.courseprogram.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
public class JwtUtil {
    private static final long expire = 604800;

    private static final String SECRET = "abcdefghijklmnopabcdefghijklmnop";
    //生成token
    public static String generateToken(String nickName){
        Date now = new Date();
        Date expiration = new Date(now.getTime()+1000*expire);
        SecretKey secretKey = new SecretKeySpec(SECRET.getBytes(),SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(nickName)
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();
    }
    public static boolean verify(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.trim());
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //解析token
    public static Claims getClaimsByToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
