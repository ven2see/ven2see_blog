package com.ven2see.springth2see.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ven2see.springth2see.model.Marketplace;
import com.ven2see.springth2see.model.User;
import com.ven2see.springth2see.model.ModelDto.MarketplaceDto;
import com.ven2see.springth2see.model.ModelDto.UserDto;
import com.ven2see.springth2see.repository.IMaketplaceRepository;

@Service
public class MPlaceService{

    private IMaketplaceRepository MPlacerepo;

    @Autowired
    private ObjectMapper objectMapper;
    
     public MPlaceService(IMaketplaceRepository repo){
          this.MPlacerepo=repo;
     }
     
    public List<MarketplaceDto> getAllProduct(){
        List<Marketplace> mentity = MPlacerepo.findAll();
        List<MarketplaceDto> marketDto = new ArrayList<>();

        for(Marketplace entity: mentity){
            marketDto.add(objectMapper.convertValue(entity, MarketplaceDto.class));
        }
        return marketDto;
    }

    public void save(MarketplaceDto MPlace){
       Marketplace market = objectMapper.convertValue(MPlace, Marketplace.class);
       MPlacerepo.save(market);
    }

    public MarketplaceDto getProductById(int id){
        MarketplaceDto marketDto = objectMapper.convertValue(MPlacerepo.findById(id), MarketplaceDto.class);
        return marketDto;
    }
}
