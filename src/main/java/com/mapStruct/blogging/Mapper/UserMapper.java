package com.mapStruct.blogging.Mapper;

import com.mapStruct.blogging.dto.UserDto;
import com.mapStruct.blogging.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = BlogMapper.class, componentModel = "spring")
public interface UserMapper {
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    List<UserDto> userListToUserDtoList(List<User> userList);
}
