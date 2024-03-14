package com.ven2see.springth2see.model.ModelDto;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.ven2see.springth2see.model.Category;
import com.ven2see.springth2see.model.User;

import lombok.Data;

@Data
public class MarketplaceDto {
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    
    @JsonIncludeProperties(value ={"id","name"})
    private Category category;

    private String image;
    
    @JsonIncludeProperties(value ={"id","name","lastname","username"})
    private User seller;

}

