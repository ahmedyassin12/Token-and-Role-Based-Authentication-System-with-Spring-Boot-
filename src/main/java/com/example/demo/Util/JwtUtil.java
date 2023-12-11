package com.example.demo.Util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class JwtUtil {


    public JwtUtil() {
    }

    //Secret_Key take and send inform from token ;
    private static final String Secret_Key ="learn_Programming_urself";


    private static final int TOKEN_VALIDITY=3600*5 ;
    //Get_Username
    public  String getUserNameFromToken (String token) {


    return getClaimFromToken(token, Claims::getSubject) ;


    }



        private <T> T getClaimFromToken(String token , Function<Claims, T> claimResolver)
        {

final Claims claims = getAllClaimsFromToken(token);

return claimResolver.apply(claims) ;


        }


        private  Claims getAllClaimsFromToken(String Token) {


        return Jwts.parser().setSigningKey(Secret_Key).parseClaimsJws(Token).getBody();


        }

        //validateToken
    public boolean validateToken    (String Token , UserDetails userDetails){


        String UserName= getUserNameFromToken(Token ) ;

        return ( UserName.equals(userDetails.getUsername()) && !isTokenExpired(Token) ) ;
        


    }

    private boolean isTokenExpired(String Token ){


            final Date dateExpiration= getExpirationDateFromToken(Token) ;

            return dateExpiration.before(new Date()) ;

    }
    private Date getExpirationDateFromToken(String Token){

        return getClaimFromToken( Token, Claims::getExpiration) ;



    }



    public String generateToken(UserDetails userDetails){

        Map<String,Object> claims=new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt  ( new Date( System.currentTimeMillis() ) )
                .setExpiration( new Date(System.currentTimeMillis() + TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512,Secret_Key)
                .compact()
                ;



    }




}
