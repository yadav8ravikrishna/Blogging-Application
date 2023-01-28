package com.mapStruct.blogging.service;

import com.mapStruct.blogging.entity.Comment;
import com.mapStruct.blogging.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

public interface CommentServiceInterface {
    String addComment(Comment comment, HttpServletRequest request, Long blogId, Long parentId) throws ResourceNotFoundException;

    String deleteComment(Long commentId) throws ResourceNotFoundException;
}
