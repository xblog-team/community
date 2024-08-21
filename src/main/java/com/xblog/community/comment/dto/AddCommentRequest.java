package com.xblog.community.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddCommentRequest {
    private String comment;
    private Long postId;
}
