package com.xblog.community.post.exception;

/**
 * 게시물을 찾지 못했을 때의 RuntimeException
 * @author jihyeon
 */
public class PostNotFoundException extends RuntimeException {
    /**
     * 게시물을 찾지 못했을 때의 exception
     * @param message 전달하고자 하는 message
     */
    public PostNotFoundException(String message) {
        super(message);
    }
}
