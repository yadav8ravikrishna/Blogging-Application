package com.mapStruct.blogging.service;

import com.mapStruct.blogging.entity.Blog;
import com.mapStruct.blogging.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

public interface BlogServiceInterface {
    Blog addBlog(Blog blog, HttpServletRequest request) throws ResourceNotFoundException;

    String deleteBlog(Long blogId) throws ResourceNotFoundException;
}
