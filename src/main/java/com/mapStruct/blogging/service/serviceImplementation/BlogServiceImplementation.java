package com.mapStruct.blogging.service.serviceImplementation;

import com.mapStruct.blogging.entity.Blog;
import com.mapStruct.blogging.entity.User;
import com.mapStruct.blogging.exception.ResourceNotFoundException;
import com.mapStruct.blogging.repo.BlogRepo;
import com.mapStruct.blogging.repo.UserRepo;
import com.mapStruct.blogging.service.BlogServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImplementation implements BlogServiceInterface {
    private final BlogRepo blogRepo;
    private final UserRepo userRepo;

    public BlogServiceImplementation(BlogRepo blogRepo, UserRepo userRepo) {
        this.blogRepo = blogRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Blog addBlog(Blog blog, HttpServletRequest request) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findUserByUserName(request.getUserPrincipal().getName());
        //Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            blog.setBlogDeleteFlag(false);
            user.get().getBlogs().add(blog);
        } else throw new ResourceNotFoundException("User Not Found");
        userRepo.save(user.get());
        return blog;
    }

    @Override
    public String deleteBlog(Long blogId) throws ResourceNotFoundException {
        Optional<Blog> blog = blogRepo.findById(blogId);
        if (blog.isPresent()) {
            blog.get().setBlogDeleteFlag(true);
            blogRepo.save(blog.get());
            return "blog Deleted";
        } else throw new ResourceNotFoundException("Blog you are trying to delete does not exist ");
    }
}
