package com.xblog.community.party.service;

/**
 * 파티(모임)와 관련된 비즈니스 로직을 정의하는 서비스 인터페이스입니다.
 * 파티의 생성, 이름 변경, 공개 여부 설정 등의 기능을 제공합니다.
 * 
 * <p>구현 클래스: {@link PartyServiceImpl}</p>
 * 
 * <ul>
 *   <li>{@link #createParty(String, Boolean)} - 새로운 파티를 생성합니다.</li>
 *   <li>{@link #changePartyName(Long, String)} - 특정 파티의 이름을 변경합니다.</li>
 *   <li>{@link #switchTo(Long, Boolean)} - 파티의 공개 여부를 설정합니다.</li>
 * </ul>
 */
public interface PartyService {
    void createParty(String partyName, Boolean isPrivate);
    void changePartyName(Long partyId, String partyName);
    void switchTo(Long partyId, Boolean isPrivate);
}