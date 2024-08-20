package com.xblog.community.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyPostResponse {
    public Long postId;
    public String content;
    public Long categoryId;
    public String userId;
}
