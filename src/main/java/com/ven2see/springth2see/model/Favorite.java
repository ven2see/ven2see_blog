package com.ven2see.springth2see.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "favorite")
public class Favorite {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     int post_id;
     Long user_id;
}
