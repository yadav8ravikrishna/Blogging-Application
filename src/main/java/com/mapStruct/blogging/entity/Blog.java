package com.mapStruct.blogging.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.Set;

@Setter
@Getter
@Where(clause = "blog_delete_flag='false'")
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String blogBody;
    private Boolean blogDeleteFlag;
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comments;
}
