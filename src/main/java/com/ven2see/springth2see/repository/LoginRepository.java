package com.ven2see.springth2see.repository;


     
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ven2see.springth2see.model.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    // Puedes agregar otros métodos personalizados según tus necesidades
}