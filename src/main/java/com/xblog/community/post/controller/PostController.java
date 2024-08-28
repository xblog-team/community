package com.xblog.community.post.controller;

import com.xblog.community.post.dto.AddPostDto;
import com.xblog.community.post.dto.GetPostResponse;
import com.xblog.community.post.dto.ModifyPostRequest;
import com.xblog.community.post.dto.ModifyPostResponse;
import com.xblog.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<AddPostDto> addPost(@RequestBody AddPostDto dto, @RequestHeader("X-User-Id") String userId) {
        AddPostDto body = postService.createPost(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable Long postId){
        GetPostResponse dto = postService.viewPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/post-categories/{categoryId}")
    public ResponseEntity<List<GetPostResponse>> getPostByCategory(@PathVariable Long categoryId){
        List<GetPostResponse> list = postService.getPostList(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ModifyPostResponse> putPost(@RequestBody ModifyPostRequest dto, @PathVariable Long postId, @RequestHeader("X-User-Id") String userId) {
        ModifyPostResponse body = postService.modifyPost(dto, postId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
