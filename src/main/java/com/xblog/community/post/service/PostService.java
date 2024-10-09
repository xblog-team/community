package com.xblog.community.post.service;

import com.xblog.community.post.dto.AddPostDto;
import com.xblog.community.post.dto.GetPostResponse;
import com.xblog.community.post.dto.ModifyPostRequest;
import com.xblog.community.post.dto.ModifyPostResponse;

import java.util.List;

public interface PostService {
    AddPostDto createPost(AddPostDto dto, String userId);
    GetPostResponse viewPost(Long postId);
    List<GetPostResponse> getPostListByParty(Long partyId);
    List<GetPostResponse> getPostListByViews(Long partyId);
    List<GetPostResponse> getPostListByCategory(Long categoryId);
    List<GetPostResponse> getPostListByCategoryAndViews(Long categoryId);
    ModifyPostResponse modifyPost(ModifyPostRequest dto, Long postId, String userId);
    void deletePost(Long postId);
}
