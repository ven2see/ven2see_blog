package com.ven2see.springth2see.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name="post")
public class Post {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     private String title;
     private String brief;
     private String content;
     private String image;
     @Transient // Esta anotación indica que este campo no se debe persistir en la base de datos
     private MultipartFile file; 
     private int status;
}

