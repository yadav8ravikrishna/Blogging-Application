package com.mapStruct.blogging.Mapper;

import com.mapStruct.blogging.dto.BlogDto;
import com.mapStruct.blogging.dto.CommentDto;
import com.mapStruct.blogging.entity.Blog;
import com.mapStruct.blogging.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface BlogMapper {
    BlogMapper blogMapper = Mappers.getMapper(BlogMapper.class);

    BlogDto blogToBlogDto(Blog blog);

    Set<BlogDto> blogSetToBLogDtoSet(Set<Blog> blogs);
}
