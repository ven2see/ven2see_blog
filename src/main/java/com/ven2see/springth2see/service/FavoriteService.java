package com.ven2see.springth2see.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ven2see.springth2see.model.Favorite;
import com.ven2see.springth2see.repository.IFavoriteRepository;


@Service
public class FavoriteService {

     public final IFavoriteRepository ifavrepo;

     public FavoriteService(IFavoriteRepository repo){
          this.ifavrepo = repo;
     }

     public Favorite insertFav(Favorite newfav){
          return ifavrepo.save(newfav);
     }

     public List<Favorite> findAllFavorite(){
          return ifavrepo.findAll();
     }

     public Optional<Favorite> getFavByid(Long id){
          return ifavrepo.findById(id);
     }

      public boolean isPostFavorite(Long userId, int postId){
          return ifavrepo.isPostFavoriteForUser(userId,postId);
     }

     public void removeByID(Long id){
          ifavrepo.deleteById(id);
     }

}
