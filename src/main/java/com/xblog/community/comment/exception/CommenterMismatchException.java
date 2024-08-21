package com.xblog.community.comment.exception;

public class CommenterMismatchException extends RuntimeException {
    public CommenterMismatchException(String message) {
        super(message);
    }
}
