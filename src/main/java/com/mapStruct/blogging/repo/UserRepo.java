package com.mapStruct.blogging.repo;

import com.mapStruct.blogging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByUserName(String userName);
}
