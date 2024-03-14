package com.ven2see.springth2see.service;

import java.util.List;

import com.ven2see.springth2see.model.ModelDto.UserDto;

public interface IUserService {
    public List<UserDto> findAll();
    public UserDto save(UserDto user);
    public void deleteById(Long id);
    public UserDto userById(Long id);
    public Boolean userExits(String username);
    public UserDto findbyUsername(String username);

    
}
