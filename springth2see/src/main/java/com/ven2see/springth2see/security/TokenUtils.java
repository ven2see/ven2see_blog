package com.ven2see.springth2see.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.ven2see.springth2see.model.ModelDto.UserDto;
import com.ven2see.springth2see.service.IUserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
     private final static String ACCEES_TOKEN_SECRET = "gY6dvLyyXuZt1w4DRkZqq9UHdKiWX0NqOpsiNRv";
     private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

public static String createToken(String nombre, String username, Collection<? extends GrantedAuthority> authorities,UserDetailsImpl userDim) {
    long expiration = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
    Date expirationDate = new Date(System.currentTimeMillis() + expiration);

    // Convertir las autoridades (roles) a una lista de cadenas
    List<String> roles = authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    Map<String, Object> extra = new HashMap<>();
    extra.put("nombre", nombre);
    extra.put("roles", roles);
    extra.put("userInfo",userDim);


    return Jwts.builder()
            .setSubject(username)
            .setExpiration(expirationDate)
            .addClaims(extra)
            .signWith(Keys.hmacShaKeyFor(ACCEES_TOKEN_SECRET.getBytes()))
            .compact();
}

     public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
          try {
               Claims claims = Jwts.parserBuilder()
                         .setSigningKey(ACCEES_TOKEN_SECRET.getBytes())
                         .build()
                         .parseClaimsJws(token)
                         .getBody();

               String username = claims.getSubject();
               Set<String> roles = (Set<String>) claims.get("roles");
               return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

          } catch (JwtException e) {
               return null;
          }
     }

}
