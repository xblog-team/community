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

/**
 * 게시물 관련 controller
 * @author jihyeon
 */
@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /**
     * 게시물을 추가하는 method
     * @param dto title, content, categoryId로 구성된 dto
     * @param userId 사용자 아이디
     * @return 201 created
     */
    @PostMapping
    public ResponseEntity<AddPostDto> addPost(@RequestBody AddPostDto dto, @RequestHeader("X-USER-ID") String userId) {
        AddPostDto body = postService.createPost(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    /**
     * 게시물을 조회하는 method
     * @param postId 게시물 아이디
     * @return 200 OK
     */
    @GetMapping("/{postId}")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable Long postId){
        GetPostResponse dto = postService.viewPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    /**
     * 특정 그룹에 속한 모든 게시물들을 최신순으로 조회하는 method
     * @param partyId 그룹 아이디
     * @return 200 OK
     */
    @GetMapping("/all/{partyId}")
    public ResponseEntity<List<GetPostResponse>> getPostByParty(@PathVariable Long partyId){
        List<GetPostResponse> dto = postService.getPostListByParty(partyId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    /**
     * 특정 그룹에 속한 모든 게시물들을 조회수 큰 순으로 조회하는 method
     * @param partyId 그룹 아이디
     * @return 200 OK
     */
    @GetMapping("/post-views/{partyId}")
    public ResponseEntity<List<GetPostResponse>> getPostByViews(@PathVariable Long partyId){
        List<GetPostResponse> dto = postService.getPostListByViews(partyId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    /**
     * 특정 카테고리에 속한 모든 게시물들을 최신순으로 조회하는 method
     * @param categoryId 카테고리 아이디
     * @return 200 OK
     */
    @GetMapping("/post-categories/{categoryId}")
    public ResponseEntity<List<GetPostResponse>> getPostByCategory(@PathVariable Long categoryId){
        List<GetPostResponse> list = postService.getPostListByCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     * 특정 카테고리에 속한 모든 게시물들을 조회수가 큰 순으로 조회하는 method
     * @param categoryId 카테고리 아이디
     * @return 200 OK
     */
    @GetMapping("/post-categories/{categoryId}/views")
    public ResponseEntity<List<GetPostResponse>> getPostByCategoryAndViews(@PathVariable Long categoryId){
        List<GetPostResponse> list = postService.getPostListByCategoryAndViews(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     * 특정 게시물을 수정하는 method
     * @param dto postId, title, content, views, categoryId, userId로 구성된 dto
     * @param postId 게시물 아이디
     * @param userId 사용자 아이디
     * @return 200 OK
     */
    @PutMapping("/{postId}")
    public ResponseEntity<ModifyPostResponse> putPost(@RequestBody ModifyPostRequest dto, @PathVariable Long postId, @RequestHeader("X-USER-ID") String userId) {
        ModifyPostResponse body = postService.modifyPost(dto, postId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    /**
     * 특정 게시물을 삭제하는 method
     * @param postId 게시물 아이디
     * @return 200 OK
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
