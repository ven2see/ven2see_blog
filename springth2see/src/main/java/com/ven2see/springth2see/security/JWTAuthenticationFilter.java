package com.ven2see.springth2see.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ven2see.springth2see.model.ModelDto.UserDto;
import com.ven2see.springth2see.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  

     @Override
     public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

          AuthCrendentials authCrendentials = new AuthCrendentials();
          try{
               authCrendentials = new ObjectMapper().readValue(request.getReader(),AuthCrendentials.class);
          }catch(java.io.IOException e){

          }

          UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
               authCrendentials.getUsername(),
               authCrendentials.getPassword(),
               Collections.emptyList()
          );

          return getAuthenticationManager().authenticate(usernamePAT);
     }

     @Override
     protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException{

         UserDetailsImpl userDetail = (UserDetailsImpl)authResult.getPrincipal();

         System.out.println(userDetail.getUsername());
         

         
         String token = TokenUtils.createToken(userDetail.getNombre(),userDetail.getUsername(),userDetail.getAuthorities(),userDetail);
          response.addHeader("Authorization", "Bearer "+token);
          response.getWriter().flush();
          
         super.successfulAuthentication(request, response, chain, authResult);
     }
}
