package com.xblog.community.exception;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(){
        super("사용자가 존재하지 않습니다.");
    }
}