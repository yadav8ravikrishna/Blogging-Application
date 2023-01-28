package com.mapStruct.blogging.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.Set;

@Setter
@Getter
@Where(clause = "comment_delete_flag='false'")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentBody;
    private Boolean commentDeleteFlag;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> commentReply;
}
