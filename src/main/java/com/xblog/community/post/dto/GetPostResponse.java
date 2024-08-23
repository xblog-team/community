package com.xblog.community.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPostResponse {
    private Long postId;
    private String title;
    private String content;
    private Long views;
    private Long CategoryId;
    private String userId;
}
