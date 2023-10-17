package com.ven2see.springth2see.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ven2see.springth2see.model.Post;
import com.ven2see.springth2see.repository.IPostRespository;

@Service
public class PostService {
     private IPostRespository repository;

     public PostService(IPostRespository repo){
          this.repository=repo;
     }

     public void insertPost(Post post){
          repository.save(post);
     }

     public List<Post> getAllPost(){
          return repository.findAll();
     }

     public Optional<Post> findPostById(Long id){
          return repository.findById(id);
     }
}
