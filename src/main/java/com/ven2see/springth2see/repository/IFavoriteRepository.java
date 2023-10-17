package com.ven2see.springth2see.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ven2see.springth2see.model.Favorite;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {

     @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Favorite f WHERE f.user_id = :userId AND f.post_id = :postId")
     boolean isPostFavoriteForUser(@Param("userId") Long userId, @Param("postId") int postId);


}
