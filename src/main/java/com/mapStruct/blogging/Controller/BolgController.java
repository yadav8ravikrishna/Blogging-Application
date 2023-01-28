package com.mapStruct.blogging.Controller;

import com.mapStruct.blogging.entity.Blog;
import com.mapStruct.blogging.exception.ResourceNotFoundException;
import com.mapStruct.blogging.service.BlogServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class BolgController {
    private final BlogServiceInterface blogServiceInterface;

    public BolgController(BlogServiceInterface blogServiceInterface) {
        this.blogServiceInterface = blogServiceInterface;
    }

    @PostMapping("/blog")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public Blog addBlog(HttpServletRequest request, @RequestBody Blog blog) throws ResourceNotFoundException {
        return blogServiceInterface.addBlog(blog, request);
    }

    @DeleteMapping("/blog/delete/{blogId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteBlog(@PathVariable Long blogId) throws ResourceNotFoundException {
        return blogServiceInterface.deleteBlog(blogId);
    }
}
