package com.mapStruct.blogging.repo;

import com.mapStruct.blogging.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
