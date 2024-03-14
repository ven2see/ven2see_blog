package com.ven2see.springth2see.model.ModelDto;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.ven2see.springth2see.model.User;

import lombok.Data;

@Data
public class PostDto {
     private Long id;
     private String title;
     private String brief;
     private String content;
     private String image;
     @JsonIncludeProperties(value={"id","name","lastname","username"})
     private User user;
     private int status;
     @JsonFormat(pattern="yyyy-MM-dd")
     private Date created_at;
     private int category;
     
     @JsonIncludeProperties(value={"id"})
     private Set<User> favoritedBy = new HashSet<>();
}

