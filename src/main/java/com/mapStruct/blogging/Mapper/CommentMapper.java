package com.mapStruct.blogging.Mapper;

import com.mapStruct.blogging.dto.CommentDto;
import com.mapStruct.blogging.dto.UserDto;
import com.mapStruct.blogging.entity.Comment;
import com.mapStruct.blogging.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "user", qualifiedByName = "noBlogs")
    CommentDto commentToCommentDto(Comment comment);

    Set<CommentDto> repliesToRepliesDto(Set<Comment> replies);

    @Named("noBlogs")
    @Mapping(target = "blogs", ignore = true)
    UserDto userToUserDtoNoBlogs(User user);
}
