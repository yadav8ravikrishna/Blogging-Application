package com.mapStruct.blogging.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String commentBody;
    private UserDto user;
    private Set<CommentDto> commentReply;
}
