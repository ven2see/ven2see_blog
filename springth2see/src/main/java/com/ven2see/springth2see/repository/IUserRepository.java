package com.ven2see.springth2see.repository;

import java.util.List;

import com.ven2see.springth2see.model.User;

public interface IUserRepository{
    public List<User> findAll();
    public int save(User User);
    public int update(User user);
    public int deleteById(int id);
    public User userById(int id);
    public User getAdmin(int tip);
    public boolean usernameExists(String username);

}