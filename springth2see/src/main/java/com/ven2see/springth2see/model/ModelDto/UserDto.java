package com.ven2see.springth2see.model.ModelDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.ven2see.springth2see.model.Marketplace;
import com.ven2see.springth2see.model.Post;
import com.ven2see.springth2see.model.Role;

import lombok.Data;

@Data
public class UserDto {
    Long id;
    String name;
    String lastname;
    String username;
    String email;
    String password;
    String image;
    int status;

    @JsonIncludeProperties(value = { "id" })
    private Set<Role> roles = new HashSet<>();

    @JsonIncludeProperties(value = { "id", "name", "description", "price", "image" })
    private List<Marketplace> marketplace;

    
   
    @JsonIncludeProperties(value = {"id","title","brief","category"})
    private Set<Post> favoritePosts = new HashSet<>();

}
