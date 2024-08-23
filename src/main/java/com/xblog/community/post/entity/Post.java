package com.xblog.community.post.entity;

import com.xblog.community.category.entity.Category;
import com.xblog.community.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "views")
    private Long views;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(Long postId, String title, String content, Long views, Category category, User user) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.views = views;
        this.category = category;
        this.user = user;
    }

    public void updateView() {
        views++;
    }
}
