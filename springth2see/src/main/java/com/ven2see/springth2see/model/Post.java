package com.ven2see.springth2see.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String title;
     private String brief;
     private String content;
     private String image;

     @ManyToOne
     @JoinColumn(name="user_id")
     private User user;

     private int status;
     
     @JsonFormat(pattern="yyyy-MM-dd")
     private Date created_at;

     @Column(name = "category_id")
     private int category;

     @ManyToMany(fetch = FetchType.EAGER) // FetchType.LAZY for performance optimization
     @JoinTable(name = "favorite", joinColumns = {
             @JoinColumn(name = "post_id", referencedColumnName = "id")
     }, inverseJoinColumns = {
             @JoinColumn(name = "user_id", referencedColumnName = "id")
     })
     private Set<User> favoritedBy = new HashSet<>();
}

