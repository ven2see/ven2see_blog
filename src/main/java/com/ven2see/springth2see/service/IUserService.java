package com.ven2see.springth2see.service;

import java.util.List;

import com.ven2see.springth2see.model.User;

public interface IUserService {
    public List<User> findAll();
    public int save(User user);
    public int update(User user);
    public int deleteById(int id);
    public User userById(int id);
    public User getAdmin(int tip);
    public boolean usernameExists(String username);

    
}
