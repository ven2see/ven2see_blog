package com.ven2see.springth2see.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ven2see.springth2see.model.Category;
import com.ven2see.springth2see.repository.ICategoRepository;

@Service
public class categoService {
    private ICategoRepository catRepo;

    public categoService(final ICategoRepository catego){
        this.catRepo = catego;
    }

    public List<Category> getAllCatego(){
        return catRepo.findAll();
    }

}
