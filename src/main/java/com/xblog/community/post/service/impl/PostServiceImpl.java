package com.xblog.community.post.service.impl;

import com.xblog.community.category.entity.Category;
import com.xblog.community.category.exception.CategoryNotFoundException;
import com.xblog.community.category.repository.CategoryRepository;
import com.xblog.community.party.entity.Party;
import com.xblog.community.party.exception.PartyNotFoundException;
import com.xblog.community.party.repository.PartyRepository;
import com.xblog.community.post.dto.AddPostDto;
import com.xblog.community.post.dto.GetPostResponse;
import com.xblog.community.post.dto.ModifyPostRequest;
import com.xblog.community.post.dto.ModifyPostResponse;
import com.xblog.community.post.entity.Post;
import com.xblog.community.post.exception.PostNotFoundException;
import com.xblog.community.post.repository.PostRepository;
import com.xblog.community.post.service.PostService;
import com.xblog.community.user.entity.User;
import com.xblog.community.user.exception.UserNotFoundException;
import com.xblog.community.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 게시물 관련 service
 * @author jihyeon
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PartyRepository partyRepository;

    /**
     * 생성된 게시물을 AddPostDto로 전환하는 method
     * @param dto title, content, categoryId로 구성된 dto
     * @param userId 사용자 아이디
     * @return title, content, categoryId로 구성된 dto
     */
    public AddPostDto createPost(AddPostDto dto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId + "라는 사용자를 찾을 수 없습니다."));
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .views(0L)
                .category(category)
                .user(user)
                .build();
        Post newPost = postRepository.save(post);

        return new AddPostDto(
                newPost.getTitle(),
                newPost.getContent(),
                newPost.getCategory().getCategoryId()
        );
    }

    /**
     * 특정 게시물을 조회하고 이를 GetPostResponse로 전환하는 method
     * @param postId 게시물 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto
     */
    public GetPostResponse viewPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));
        post.updateView();
        Post newPost = postRepository.save(post);

        return new GetPostResponse(
                newPost.getPostId(),
                newPost.getTitle(),
                newPost.getContent(),
                newPost.getViews(),
                newPost.getCategory().getCategoryId(),
                newPost.getUser().getUserId()
        );
    }

    /**
     * 그룹에 속한 모든 게시물 중 최신순으로 9개 이하만 가져오는 method
     * @param partyId 그룹 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
    @Override
    public List<GetPostResponse> getPostListByParty(Long partyId) {
        Party party = partyRepository.findById(partyId).orElseThrow(() -> new PartyNotFoundException("해당 그룹을 찾지 못했습니다."));
        List<Post> postList = postRepository.findByCategory_PartyOrderByPostIdDesc(party);
        List<GetPostResponse> responseList = new ArrayList<>();

        int size = postList.size() > 9 ? 9 : postList.size();
        for (int i = 0; i < size; i++) {
            Post post = postList.get(i);

            GetPostResponse getPost = new GetPostResponse(
                    post.getPostId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getViews(),
                    post.getCategory().getCategoryId(),
                    post.getUser().getUserId());
            responseList.add(getPost);
        }
        return responseList;
    }

    /**
     * 그룹에 속한 모든 게시물 중 조회수가 큰 순으로 9개 이하만 가져오는 method
     * @param partyId 그룹 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
    @Override
    public List<GetPostResponse> getPostListByViews(Long partyId) {
        Party party = partyRepository.findById(partyId).orElseThrow(() -> new PartyNotFoundException("해당 그룹을 찾지 못했습니다."));
        List<Post> postList = postRepository.findByCategory_PartyOrderByViewsDesc(party);
        List<GetPostResponse> responseList = new ArrayList<>();

        int size = postList.size() > 10 ? 9 : postList.size();
        for (int i = 0; i < size; i++) {
            Post post = postList.get(i);

            GetPostResponse getPost = new GetPostResponse(
                    post.getPostId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getViews(),
                    post.getCategory().getCategoryId(),
                    post.getUser().getUserId());
            responseList.add(getPost);
        }
        return responseList;
    }

    /**
     * 카테고리에 속한 모든 게시물 중 최신순으로 9개 이하만 가져오는 method
     * @param categoryId 카테고리 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
    public List<GetPostResponse> getPostListByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
        List<Post> postList = postRepository.findByCategory_CategoryId(category.getCategoryId());
        List<GetPostResponse> responseList = new ArrayList<>();

        for (Post post : postList) {
            GetPostResponse getPost = new GetPostResponse(
                    post.getPostId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getViews(),
                    post.getCategory().getCategoryId(),
                    post.getUser().getUserId());
            responseList.add(getPost);
        }
        return responseList;
    }

    /**
     * 카테고리에 속한 모든 게시물 중 조회수가 큰 순으로 9개 이하만 가져오는 method
     * @param categoryId 카테고리 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
    @Override
    public List<GetPostResponse> getPostListByCategoryAndViews(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
        List<Post> postList = postRepository.findByCategory_CategoryIdOrderByViewsDesc(category.getCategoryId());
        List<GetPostResponse> responseList = new ArrayList<>();

        for (Post post : postList) {
            GetPostResponse getPost = new GetPostResponse(
                    post.getPostId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getViews(),
                    post.getCategory().getCategoryId(),
                    post.getUser().getUserId());
            responseList.add(getPost);
        }
        return responseList;
    }

    /**
     * 게시물을 수정하는 method
     * @param dto title, content, categoryId로 이루어진 dto
     * @param postId 게시물 아이디
     * @param userId 사용자 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto
     */
    public ModifyPostResponse modifyPost(ModifyPostRequest dto, Long postId, String userId) {
        Post p = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId + "라는 사용자를 찾을 수 없습니다."));
        Post post = Post.builder()
                .postId(postId)
                .category(category)
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .views(p.getViews())
                .build();
        Post modifyPost = postRepository.save(post);
        return new ModifyPostResponse(
                modifyPost.getPostId(),
                modifyPost.getTitle(),
                modifyPost.getContent(),
                modifyPost.getViews(),
                modifyPost.getCategory().getCategoryId(),
                modifyPost.getUser().getUserId()
        );
    }

    /**
     * 게시물을 삭제하는 method
     * @param postId 게시물 아이디
     */
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));
        postRepository.delete(post);
    }

}
