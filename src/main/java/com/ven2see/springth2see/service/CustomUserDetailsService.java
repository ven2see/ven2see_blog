package com.ven2see.springth2see.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ven2see.springth2see.model.Role;
import com.ven2see.springth2see.model.User;
import com.ven2see.springth2see.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository userRepository;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow();

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), getAuthority(user.getRoles()));
    }


    private Collection<? extends GrantedAuthority> getAuthority(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName()))
                .collect(Collectors.toList());
    }

}
