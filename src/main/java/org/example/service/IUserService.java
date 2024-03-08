package org.example.service;

import org.example.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();

    void createUser(UserDto userDto);
}
