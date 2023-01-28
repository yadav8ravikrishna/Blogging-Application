package com.mapStruct.blogging.service;

import com.mapStruct.blogging.dto.UserDto;
import com.mapStruct.blogging.entity.User;
import com.mapStruct.blogging.exception.ResourceNotFoundException;

import java.util.List;

public interface UserServiceInterface {
    User addUser(User user);

    UserDto getUser(Long userId) throws ResourceNotFoundException;

    List<UserDto> getAllUsers();

    String deleteUser(Long userId) throws ResourceNotFoundException;
}
