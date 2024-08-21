package com.xblog.community.post.service.impl;

import com.xblog.community.category.entity.Category;
import com.xblog.community.category.exception.CategoryNotFoundException;
import com.xblog.community.category.repository.CategoryRepository;
import com.xblog.community.post.dto.AddPostDto;
import com.xblog.community.post.dto.GetPostResponse;
import com.xblog.community.post.dto.ModifyPostRequeset;
import com.xblog.community.post.dto.ModifyPostResponse;
import com.xblog.community.post.entity.Post;
import com.xblog.community.post.exception.PostNotFoundException;
import com.xblog.community.post.repository.PostReposiotry;
import com.xblog.community.post.service.PostService;
import com.xblog.community.user.entity.User;
import com.xblog.community.user.exception.UserNotFoundException;
import com.xblog.community.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostReposiotry postReposiotry;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public AddPostDto createPost(AddPostDto dto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId + "라는 사용자를 찾을 수 없습니다."));
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));

        Post post = Post.builder()
                .category(category)
                .content(dto.getContent())
                .user(user)
                .build();
        Post newPost = postReposiotry.save(post);

        return new AddPostDto(
                newPost.getContent(),
                newPost.getCategory().getCategoryId()
        );
    }

    public GetPostResponse viewPost(Long postId) {
        Post post = postReposiotry.findById(postId).orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));
        return new GetPostResponse(
                post.getPostId(),
                post.getContent(),
                post.getCategory().getCategoryId(),
                post.getUser().getUserId()
        );
    }

    public List<GetPostResponse> getPostList(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
        List<Post> postList = postReposiotry.findByCategory_CategoryId(category.getCategoryId());
        List<GetPostResponse> responseList = new ArrayList<>();

        for (Post post : postList) {
            GetPostResponse getPost = new GetPostResponse(
                    post.getPostId(),
                    post.getContent(),
                    post.getCategory().getCategoryId(),
                    post.getUser().getUserId());
            responseList.add(getPost);
        }
        return responseList;
    }

    public ModifyPostResponse modifyPost(ModifyPostRequeset dto, Long postId, String userId) {
        postReposiotry.findById(postId).orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId + "라는 사용자를 찾을 수 없습니다."));
        Post post = Post.builder()
                .postId(postId)
                .category(category)
                .content(dto.getContent())
                .user(user)
                .build();
        Post modifyPost = postReposiotry.save(post);
        return new ModifyPostResponse(
                modifyPost.getPostId(),
                modifyPost.getContent(),
                modifyPost.getCategory().getCategoryId(),
                modifyPost.getUser().getUserId()
        );
    }

    public void deletePost(Long postId) {
        Post post = postReposiotry.findById(postId).orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));
        postReposiotry.delete(post);
    }

}
