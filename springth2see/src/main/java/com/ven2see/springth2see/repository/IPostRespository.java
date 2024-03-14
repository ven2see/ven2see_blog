package com.ven2see.springth2see.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.ven2see.springth2see.model.Post;

@Repository
public interface IPostRespository extends JpaRepository<Post,Long>{
     List<Post> findAllByOrderByIdDesc();

}
