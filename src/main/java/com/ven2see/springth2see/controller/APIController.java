package com.ven2see.springth2see.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ven2see.springth2see.model.ServiceResponse;
import com.ven2see.springth2see.model.User;
import com.ven2see.springth2see.repository.LoginRepository;
import com.ven2see.springth2see.service.FavoriteService;

@RestController
public class APIController {

     @Autowired
     private FavoriteService fService;

     @Autowired
     private LoginRepository userRepository;

     public User userAuth() {
          Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          UserDetails userDetails = null;
          if (principal instanceof UserDetails) {
               userDetails = (UserDetails) principal;
          }

          String username = userDetails.getUsername();
          System.out.println(username);
          Optional<User> opUser = userRepository.findByUsername(username);
          User user = opUser.get();
          return user;
     }

     @RequestMapping(value = "/boolean/{postId}", method = RequestMethod.GET)
     public ResponseEntity<ServiceResponse> addToFavorites(@PathVariable int postId, Principal principal) {
          // Lógica para determinar si el post es favorito para el usuario
          User user = userAuth();
          boolean isFavorite = fService.isPostFavorite(user.getId(), postId);
          if(isFavorite){
               fService.removeByID(Long.valueOf(postId));
          }
          ServiceResponse response = new ServiceResponse();
          response.setSuccess(isFavorite);
          return ResponseEntity.ok(response);
     }
}
