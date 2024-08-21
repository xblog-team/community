package com.xblog.community.exception;

public class IncorrectPassword extends RuntimeException{
    public IncorrectPassword(){
        super("비밀번호가 틀렸습니다.");
    }
}