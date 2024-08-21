package com.xblog.community.comment.service;

import com.xblog.community.comment.dto.AddCommentRequest;
import com.xblog.community.comment.dto.GetCommentResponse;
import com.xblog.community.comment.dto.ModifyCommentRequest;

import java.util.List;

public interface CommentService {
    void createComment(String userId, AddCommentRequest request);

    GetCommentResponse viewComment(Long commentId);

    List<GetCommentResponse> getCommentList(Long postId);

    GetCommentResponse modifyComment(Long commentId, ModifyCommentRequest request, String userId);

    void deleteComment(Long commentId);
}
