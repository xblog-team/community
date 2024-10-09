package com.xblog.community.user_party.exception;

public class AlreadyInPartyException extends RuntimeException{
    public AlreadyInPartyException(){
        super("이미 가입되어 있습니다.");
    }
}