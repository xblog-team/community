package com.xblog.community.comment.entity;

import antlr.build.ANTLR;
import com.xblog.community.post.entity.Post;
import com.xblog.community.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Comment(Long commentId, String comment, Post post, User user) {
        this.commentId = commentId;
        this.comment = comment;
        this.post = post;
        this.user = user;
    }
}
