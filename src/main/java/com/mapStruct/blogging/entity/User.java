package com.mapStruct.blogging.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Where(clause = "user_delete_flag='false'")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;
    @OneToOne(targetEntity = Roles.class, cascade = CascadeType.ALL)
    private Roles role;
    private String userName;
    private Boolean userDeleteFlag;
    @OneToMany(targetEntity = Blog.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Blog> blogs;
}
