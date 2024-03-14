package com.ven2see.springth2see.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "marketplace")
@Data
public class Marketplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "description")
    private String description;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    private String image;

    @ManyToOne
    @JoinColumn(name = "saller_id", nullable=false)
    private User seller;

}
