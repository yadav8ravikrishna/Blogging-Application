package com.mapStruct.blogging.Controller;

import com.mapStruct.blogging.entity.Comment;
import com.mapStruct.blogging.exception.ResourceNotFoundException;
import com.mapStruct.blogging.service.CommentServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private final CommentServiceInterface commentServiceInterface;

    public CommentController(CommentServiceInterface commentServiceInterface) {
        this.commentServiceInterface = commentServiceInterface;
    }

    @PostMapping("/comment/blog/{blogId}/parent/{parentId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public String addComment(@RequestBody Comment comment, HttpServletRequest request, @PathVariable Long blogId, @PathVariable Long parentId) throws ResourceNotFoundException {
        return commentServiceInterface.addComment(comment, request, blogId, parentId);
    }

    @DeleteMapping("/comment/delete/{commentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteComment(@PathVariable Long commentId) throws ResourceNotFoundException {
        return commentServiceInterface.deleteComment(commentId);
    }
}
