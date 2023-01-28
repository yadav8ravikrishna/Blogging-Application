package com.mapStruct.blogging.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BlogDto {
    private Long id;
    private String blogBody;
    private Set<CommentDto> comments;
}
