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
    public ResponseEntity<AddPostDto> addPost(@RequestBody AddPostDto dto, @RequestHeader("X-USER-ID") String userId) {
        AddPostDto body = postService.createPost(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable Long postId){
        GetPostResponse dto = postService.viewPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/all/{partyId}")
    public ResponseEntity<List<GetPostResponse>> getPostByParty(@PathVariable Long partyId){
        List<GetPostResponse> dto = postService.getPostListByParty(partyId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/post-views/{partyId}")
    public ResponseEntity<List<GetPostResponse>> getPostByViews(@PathVariable Long partyId){
        List<GetPostResponse> dto = postService.getPostListByViews(partyId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/post-categories/{categoryId}")
    public ResponseEntity<List<GetPostResponse>> getPostByCategory(@PathVariable Long categoryId){
        List<GetPostResponse> list = postService.getPostListByCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/post-categories/{categoryId}/views")
    public ResponseEntity<List<GetPostResponse>> getPostByCategoryAndViews(@PathVariable Long categoryId){
        List<GetPostResponse> list = postService.getPostListByCategoryAndViews(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ModifyPostResponse> putPost(@RequestBody ModifyPostRequest dto, @PathVariable Long postId, @RequestHeader("X-USER-ID") String userId) {
        ModifyPostResponse body = postService.modifyPost(dto, postId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
