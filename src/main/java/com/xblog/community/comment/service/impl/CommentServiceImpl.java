package com.xblog.community.comment.service.impl;

import com.xblog.community.comment.dto.AddCommentRequest;
import com.xblog.community.comment.dto.GetCommentResponse;
import com.xblog.community.comment.dto.ModifyCommentRequest;
import com.xblog.community.comment.entity.Comment;
import com.xblog.community.comment.exception.CommentNotFoundException;
import com.xblog.community.comment.exception.CommenterMismatchException;
import com.xblog.community.comment.service.CommentService;
import com.xblog.community.comment.service.repository.CommentRepository;
import com.xblog.community.post.entity.Post;
import com.xblog.community.post.exception.PostNotFoundException;
import com.xblog.community.post.repository.PostRepository;
import com.xblog.community.user.entity.User;
import com.xblog.community.user.exception.UserNotFoundException;
import com.xblog.community.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postReposiotry;

    @Override
    public void createComment(String userId, AddCommentRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId + "라는 사용자를 찾을 수 없습니다."));
        Post post = postReposiotry.findById(request.getPostId())
                .orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .comment(request.getComment())
                .post(post)
                .user(user)
                .build();
        commentRepository.save(comment);
    }

    @Override
    public GetCommentResponse viewComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("해당 댓글을 찾을 수 없습니다."));
        return new GetCommentResponse(
                comment.getCommentId(),
                comment.getComment(),
                comment.getPost().getPostId(),
                comment.getUser().getUserId()
        );
    }

    @Override
    public List<GetCommentResponse> getCommentList(Long postId) {
        postReposiotry.findById(postId).orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));
        List<Comment> commentList = commentRepository.findByPost_PostId(postId);
        List<GetCommentResponse> responseList = new ArrayList<>();
        for (Comment comment : commentList) {
            GetCommentResponse response = new GetCommentResponse(
                    comment.getCommentId(),
                    comment.getComment(),
                    comment.getPost().getPostId(),
                    comment.getUser().getUserId()
            );
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public GetCommentResponse modifyComment(Long commentId, ModifyCommentRequest request, String userId) {
        Post post = postReposiotry.findById(request.getPostId()).orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId + "라는 사용자를 찾을 수 없습니다."));
        Comment  comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("해당 댓글을 찾을 수 없습니다."));
        if(!Objects.equals(comment.getUser().getUserId(), userId)) {
            throw new CommenterMismatchException("이전에 댓글 단 사람과 현재 댓글 수정하는 이가 다릅니다.");
        }
        Comment newComment = Comment.builder()
                .commentId(commentId)
                .comment(request.getComment())
                .post(post)
                .user(user)
                .build();

        commentRepository.save(newComment);
        return new GetCommentResponse(
                newComment.getCommentId(),
                newComment.getComment(),
                newComment.getPost().getPostId(),
                newComment.getUser().getUserId()
        );
    }

    @Override
    public void deleteComment(Long commentId) {
        Boolean existComment = commentRepository.existsById(commentId);
        if(existComment) {
            commentRepository.deleteById(commentId);
        } else {
            throw new CommentNotFoundException("해당 댓글을 찾을 수 없습니다.");
        }

    }
}
