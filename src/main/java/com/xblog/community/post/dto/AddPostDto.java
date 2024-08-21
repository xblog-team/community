package com.xblog.community.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddPostDto {
    public String content;
    public Long categoryId;
}
