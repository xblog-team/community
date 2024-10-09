package com.xblog.community.user.service;

import com.xblog.community.user.dto.LoginInfoResponseDto;
import com.xblog.community.user.dto.RegisterUserRequestDto;
import com.xblog.community.user.dto.RegisterUserResponseDto;
import com.xblog.community.user.dto.UpdatePasswordRequestDto;

/**
 * 사용자 관련 서비스 인터페이스입니다.
 * 이 인터페이스는 사용자 등록, 정보 조회, 비밀번호 변경 등 사용자 관리와 관련된 메서드를 정의합니다.
 */
public interface UserService {
    
    /**
     * 주어진 사용자 ID에 대한 로그인 정보를 조회합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @return 사용자의 로그인 정보 응답 DTO
     */
    LoginInfoResponseDto getLoginInfo(String userId);

    /**
     * 주어진 사용자 ID에 대한 사용자의 닉네임을 조회합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @return 사용자의 닉네임
     */
    String getUserNickname(String userId);

    /**
     * 주어진 사용자 ID가 존재하는지 확인합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @return 사용자 존재 여부
     */
    Boolean userExists(String userId);
    
    /**
     * 새로운 사용자를 등록합니다.
     *
     * @param registerUserRequestDto 사용자 등록 요청 DTO
     * @return 등록된 사용자의 응답 DTO
     */
    RegisterUserResponseDto createUser(RegisterUserRequestDto registerUserRequestDto);

    /**
     * 주어진 사용자 ID에 대해 사용자를 탈퇴 처리합니다.
     *
     * @param userId 사용자의 고유 식별자
     */
    void withdrawUser(String userId);

    /**
     * 주어진 사용자 ID에 대해 사용자의 계정을 비활성화하거나 활성화합니다.
     *
     * @param userId 사용자의 고유 식별자
     */
    void disableOrEnableUser(String userId);

    /**
     * 주어진 사용자 ID의 비밀번호를 업데이트합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @param updatePasswordRequestDto 비밀번호 업데이트 요청 DTO
     */
    void updatePassword(String userId, UpdatePasswordRequestDto updatePasswordRequestDto);

    /**
     * 주어진 사용자 ID의 닉네임을 변경합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @param newNickname 새 닉네임
     */
    void changeNickname(String userId, String newNickname);

    /**
     * 주어진 사용자 ID의 권한을 변경합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @param authorityId 새로운 권한 ID
     */
    void changeAuthority(String userId, Short authorityId);
}
