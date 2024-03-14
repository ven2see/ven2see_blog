package com.ven2see.springth2see.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user_blog")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String name;
        String lastname;
        String username;
        String email;
        String password;
        String image;
        int status;

        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
        @JoinTable(name = "users_roles", joinColumns = {
                        @JoinColumn(name = "user_id", referencedColumnName = "id")
        }, inverseJoinColumns = {
                        @JoinColumn(name = "role_id", referencedColumnName = "id")
        })

        private Set<Role> roles = new HashSet<>();


        @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
        @JsonIncludeProperties(value = {"id","name","description","price","image"})
        private List<Marketplace> marketplace = new ArrayList<>();


        @ManyToMany(mappedBy = "favoritedBy", fetch = FetchType.EAGER) 
         @JsonIncludeProperties(value = {"id","title","brief","category"})
        private Set<Post> favoritePosts = new HashSet<>();

}

