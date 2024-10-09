package com.xblog.community.party.exception;

/**
 * 그룹을 찾지 못했을 경우의 RuntimeException
 * @author jihyeon
 */
public class PartyNotFoundException extends RuntimeException {
    /**
     * 그룹을 찾지 못했을 경우의 exception
     * @param message 전달하고자 하는 message
     */
    public PartyNotFoundException(String message) {
        super(message);
    }
}
