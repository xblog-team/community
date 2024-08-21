package com.xblog.community.post.service;

import com.xblog.community.post.dto.AddPostDto;
import com.xblog.community.post.dto.GetPostResponse;
import com.xblog.community.post.dto.ModifyPostRequeset;
import com.xblog.community.post.dto.ModifyPostResponse;

import java.util.List;

public interface PostService {
    AddPostDto createPost(AddPostDto dto, String userId);
    GetPostResponse viewPost(Long postId);
    List<GetPostResponse> getPostList(Long categoryId);
    ModifyPostResponse modifyPost(ModifyPostRequeset dto, Long postId, String userId);
    void deletePost(Long postId);
}
