package com.ven2see.springth2see.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ven2see.springth2see.model.Post;
import com.ven2see.springth2see.model.ModelDto.PostDto;
import com.ven2see.springth2see.repository.IPostRespository;

@Service
public class PostService {
     private IPostRespository repository;

     @Autowired
     private ObjectMapper objectMapper;

     public PostService(IPostRespository repo){
          this.repository=repo;
     }

     public void insertPost(PostDto post){
          Post postEntity = objectMapper.convertValue(post, Post.class);
          repository.save(postEntity);
     }

     public List<PostDto> getAllPost(){
          List<Post> posts = repository.findAllByOrderByIdDesc();
          List<PostDto> postsDto = new ArrayList<>();
          for(Post post: posts){
               postsDto.add(objectMapper.convertValue(post, PostDto.class));
          }
          return postsDto;
     }

     public PostDto findPostById(Long id){
          return objectMapper.convertValue(repository.findById(id),PostDto.class);
     }

}
