package com.mapStruct.blogging.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String userName;
    private Set<BlogDto> blogs;
}
