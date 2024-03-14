package com.ven2see.springth2see.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ven2see.springth2see.model.User;
import com.ven2see.springth2see.model.ModelDto.UserDto;
import com.ven2see.springth2see.repository.UserRepository;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<UserDto> findAll() {
        List<User> userEntities= userRepository.findAll();
        List<UserDto> usersDtos = new ArrayList<>();
        for(User userEntity : userEntities){
            usersDtos.add(objectMapper.convertValue(userEntity, UserDto.class));
        }
        return usersDtos;
    }

    @Override
    public UserDto userById(Long id) {
        UserDto user = objectMapper.convertValue(userRepository.findById(id), UserDto.class);
        return user;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User userEntityToSave = objectMapper.convertValue(userDto, User.class);
        userRepository.save(userEntityToSave);
        return userDto;
    }

    @Override
    public void deleteById(Long id) {
        UserDto userFound =  userById(id);
        if (userFound != null)
            userRepository.deleteById(id);
    }

    @Override
    public Boolean userExits(String username) {
        if(userRepository.findByUsername(username)!=null){
            return true;
        }
       return false;
    }

    @Override
    public UserDto findbyUsername(String username){
        UserDto userDto = objectMapper.convertValue(userRepository.findByUsername(username), UserDto.class);
        return userDto;
    }
}
