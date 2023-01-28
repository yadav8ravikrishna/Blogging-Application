package com.mapStruct.blogging.service.serviceImplementation;

import com.mapStruct.blogging.entity.Blog;
import com.mapStruct.blogging.entity.Comment;
import com.mapStruct.blogging.entity.User;
import com.mapStruct.blogging.exception.ResourceNotFoundException;
import com.mapStruct.blogging.repo.BlogRepo;
import com.mapStruct.blogging.repo.CommentRepo;
import com.mapStruct.blogging.repo.UserRepo;
import com.mapStruct.blogging.service.CommentServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentServiceInterface {
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final BlogRepo blogRepo;

    public CommentServiceImplementation(CommentRepo commentRepo, UserRepo userRepo, BlogRepo blogRepo) {
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.blogRepo = blogRepo;
    }

    @Override
    public String addComment(Comment comment, HttpServletRequest request, Long blogId, Long parentId) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findUserByUserName(request.getUserPrincipal().getName());
        Optional<Blog> blog = blogRepo.findById(blogId);
        if (blog.isPresent()) {
            if (user.isPresent()) {
                comment.setCommentDeleteFlag(false);
                comment.setUser(user.get());
                if (parentId == 0) {
                    blog.get().getComments().add(comment);
                    blogRepo.save(blog.get());
                } else {
                    Optional<Comment> comment1 = commentRepo.findById(parentId);
                    comment1.get().getCommentReply().add(comment);
                    commentRepo.save(comment1.get());
                }
            } else throw new ResourceNotFoundException("User Not Found!!");

        } else throw new ResourceNotFoundException("Blog Not Found!!");
        return "Comment Added";
    }

    @Override
    public String deleteComment(Long commentId) throws ResourceNotFoundException {
        Optional<Comment> comment = commentRepo.findById(commentId);
        if (comment.isPresent()) {
            comment.get().setCommentDeleteFlag(true);
            commentRepo.save(comment.get());
            return "Comment Deleted";
        } else throw new ResourceNotFoundException("Comment not present");
    }
}
