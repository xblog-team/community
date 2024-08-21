package com.xblog.community.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPostResponse {
    public Long postId;
    public String content;
    public Long CategoryId;
    public String userId;
}
