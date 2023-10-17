package com.ven2see.springth2see.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ven2see.springth2see.model.User;
import com.ven2see.springth2see.repository.IUserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public List<User> findAll() {
        List<User> list;
        try {
            list = iUserRepository.findAll();
        } catch (Exception e) {
            throw e;
        }

        return list;
    }

    @Override
    public User userById(int id) {
        User user = iUserRepository.userById(id);
        if (user == null) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        return user;
    }

    @Override
    public int save(User user) {
        int row;
        try {
            row = iUserRepository.save(user);
        } catch (Exception e) {
            throw e;
        }

        return row;
    }

    public User getAdmin(int tip) {
        User user;
        try {
            user = iUserRepository.getAdmin(tip);
        } catch (Exception e) {
            throw e;
        }

        return user;
    }

    @Override
    public int update(User user) {
        int row;
        try {
            row = iUserRepository.update(user);
        } catch (Exception e) {
            throw e;
        }

        return row;
    }

    @Override
    public int deleteById(int id) {
        int row;
        try {
            row = iUserRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }

        return row;
    }
    @Override
    public boolean usernameExists(String username){
        return iUserRepository.usernameExists(username);
    }
}
