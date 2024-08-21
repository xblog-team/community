package com.xblog.community.comment.controller;

import com.xblog.community.comment.dto.AddCommentRequest;
import com.xblog.community.comment.dto.GetCommentResponse;
import com.xblog.community.comment.dto.ModifyCommentRequest;
import com.xblog.community.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<Void> addComment(@RequestHeader("X-User-Id")String userId, @RequestBody AddCommentRequest request) {
        commentService.createComment(userId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<GetCommentResponse> getComment(@PathVariable("commentId") Long commentId) {
        GetCommentResponse response = commentService.viewComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/comments/{postId}")
    public ResponseEntity<List<GetCommentResponse>> getCommentByPostId(@PathVariable("postId") Long postId) {
        List<GetCommentResponse> list = commentService.getCommentList(postId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<GetCommentResponse> updateComment(@PathVariable("commentId") Long commentId, @RequestBody ModifyCommentRequest request, @RequestHeader("X-User-Id")String userId) {
        GetCommentResponse response = commentService.modifyComment(commentId, request, userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
