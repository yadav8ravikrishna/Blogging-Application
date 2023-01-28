package com.mapStruct.blogging.service.serviceImplementation;

import com.mapStruct.blogging.Mapper.UserMapper;
import com.mapStruct.blogging.config.SecurityConfig;
import com.mapStruct.blogging.dto.UserDto;
import com.mapStruct.blogging.entity.User;
import com.mapStruct.blogging.exception.ResourceNotFoundException;
import com.mapStruct.blogging.repo.UserRepo;
import com.mapStruct.blogging.service.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserServiceInterface {
    private final SecurityConfig securityConfig;
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserServiceImplementation(SecurityConfig securityConfig, UserRepo userRepo, UserMapper userMapper) {
        this.securityConfig = securityConfig;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public User addUser(User user) {
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setUserDeleteFlag(false);
        return userRepo.save(user);
    }

    @Override
    public UserDto getUser(Long userId) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            return userMapper.userToUserDto(user.get());
        } else {
            throw new ResourceNotFoundException("User Not Found!!!!");
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.userListToUserDtoList(userRepo.findAll());
    }

    @Override
    public String deleteUser(Long userId) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            user.get().setUserDeleteFlag(true);
            userRepo.save(user.get());
            return "User " + user.get().getUserName() + " deleted";
        } else throw new ResourceNotFoundException("User you are trying to delete does not exist");
    }
}
