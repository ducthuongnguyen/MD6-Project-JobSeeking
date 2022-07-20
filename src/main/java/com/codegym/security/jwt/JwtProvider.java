package com.codegym.security.jwt;

import com.codegym.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
//lop nay tao ra token
public class JwtProvider {
    //ghi logger  org.slf4j
    private static final Logger loger = LoggerFactory.getLogger(JwtProvider.class);
    // bien cay truyen 1 cay de dky cay
    private String jwtSecret="chinh.nguyen@codegym.vn";
    //lam tgian song cua 1 ngay theo giay
    private int jwtExpiration=86400;
    //hamf tao token
    public String createToken(Authentication authentication){
        //get ra user hien tai tren he thong
        UserPrinciple userPrinciple= (UserPrinciple) authentication.getPrincipal();
        // tra ve lop co san de build 1 cai token
        return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }
    //ham check token hop le hay ko
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            loger.error("Invalid JWT signature --> Message:{}",e);
        }catch (MalformedJwtException e){
            loger.error("Invalid format Token-->Message:{}",e);
        }catch (ExpiredJwtException e){
            loger.error("Expire JWT token -->Message:{}",e);
        }catch (UnsupportedJwtException e){
            loger.error("Unsupported JWT token-->Message:{}",e);
        }catch (IllegalArgumentException e){
            loger.error("JWT claim string is empty -->Message:{}",e);
        }
        return false;
    }
    public String getUserNameToken(String token){
        String userName=Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }

}
