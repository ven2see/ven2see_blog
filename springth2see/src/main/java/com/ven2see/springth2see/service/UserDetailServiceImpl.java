package com.ven2see.springth2see.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.ven2see.springth2see.model.User;
import com.ven2see.springth2see.repository.LoginRepository;
import com.ven2see.springth2see.security.UserDetailsImpl;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
     @Autowired
     private LoginRepository userRepository;

     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          User user = userRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("The user has not registry"));
          return new UserDetailsImpl(user);

     }

}
