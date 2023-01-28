package com.mapStruct.blogging.repo;

import com.mapStruct.blogging.entity.Blog;
import com.mapStruct.blogging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog, Long> {
}
