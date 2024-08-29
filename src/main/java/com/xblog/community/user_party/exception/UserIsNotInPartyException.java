package com.xblog.community.user_party.exception;

public class UserIsNotInPartyException extends RuntimeException{
    public UserIsNotInPartyException(){
        super("사용자는 가입되어 있지 않습니다.");
    }
}
