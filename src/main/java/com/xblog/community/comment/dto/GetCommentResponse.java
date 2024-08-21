package com.xblog.community.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCommentResponse {
    private Long commentId;
    private String comment;
    private Long postId;
    private String userId;
}
