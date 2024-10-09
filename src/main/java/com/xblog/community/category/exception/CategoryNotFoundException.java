package com.xblog.community.category.exception;

/**
 * 카테고리를 찾을 수 없을 경우의 RuntimeException
 * @author jihyeon
 */
public class CategoryNotFoundException extends RuntimeException{
    /**
     * 카테고리를 찾을 수 없는 경우의 exception
     * @param message 전달하고자 하는 message
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
