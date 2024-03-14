package com.ven2see.springth2see.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ven2see.springth2see.model.Category;

@Repository
public interface ICategoRepository extends JpaRepository<Category,Integer>{
    
}
