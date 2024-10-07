package com.xblog.community.user.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xblog.community.authority.entity.Authority;
import com.xblog.community.authority.repository.AuthorityRepository;
import com.xblog.community.exception.IncorrectPassword;
import com.xblog.community.exception.UserAlreadyExistsException;
import com.xblog.community.exception.UserDoesNotExistException;
import com.xblog.community.user.dto.LoginInfoResponseDto;
import com.xblog.community.user.dto.RegisterUserRequestDto;
import com.xblog.community.user.dto.RegisterUserResponseDto;
import com.xblog.community.user.dto.UpdatePasswordRequestDto;
import com.xblog.community.user.entity.User;
import com.xblog.community.user.repository.UserRepository;
import com.xblog.community.user.service.UserService;
import com.xblog.community.user_role.entity.UserRole;
import com.xblog.community.user_role.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 서비스 구현 클래스입니다.
 * 이 클래스는 사용자 등록, 정보 조회, 비밀번호 변경 등 사용자 관련 비즈니스 로직을 구현합니다.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    /**
     * 주어진 사용자 ID에 대한 로그인 정보를 조회합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @return 사용자의 로그인 정보 응답 DTO
     * @throws UserDoesNotExistException 사용자가 존재하지 않을 경우 발생합니다.
     */
    @Override
    public LoginInfoResponseDto getLoginInfo(String userId) {
        
        User user = userRepository.findById(userId).orElseThrow(() -> new UserDoesNotExistException());
        
        List<UserRole> userRoles = userRoleRepository.getByUserId(userId);

        List<String> roleList = userRoles.stream()
        .map(userRole -> authorityRepository.findById(userRole.getAuthorityId())
            .map(Authority::getAuthority)
            .orElseThrow(() -> new IllegalStateException("권한 존재하지 않습니다.")))
        .collect(Collectors.toList());
        
        return new LoginInfoResponseDto(user.getUserId(), user.getPassword(), roleList);
    }

    /**
     * 주어진 사용자 ID가 존재하는지 확인합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @return 사용자 존재 여부
     */
    @Override
    public Boolean userExists(String userId) {
        return userRepository.existsById(userId);
    }
    
    /**
     * 새로운 사용자를 등록합니다.
     *
     * @param registerUserRequestDto 사용자 등록 요청 DTO
     * @return 등록된 사용자의 응답 DTO
     * @throws UserAlreadyExistsException 사용자가 이미 존재하는 경우 발생합니다.
     */
    @Transactional
    @Override
    public RegisterUserResponseDto createUser(RegisterUserRequestDto registerUserRequestDto) {
        
        if (userExists(registerUserRequestDto.getUserId())) {
            throw new UserAlreadyExistsException();
        }
        
        User user = new User();
        user.setUserId(registerUserRequestDto.getUserId());
        user.setNickname(registerUserRequestDto.getNickname());
        user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setAuthorityId(registerUserRequestDto.getAuthorityId());
        userRole.setUserId(registerUserRequestDto.getUserId());
        userRoleRepository.save(userRole);
        
        return new RegisterUserResponseDto(user.getUserId(),user.getNickname());
    }

    /**
     * 주어진 사용자 ID에 대해 사용자를 탈퇴 처리합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @throws UserDoesNotExistException 사용자가 존재하지 않을 경우 발생합니다.
     */
    @Override
    public void withdrawUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (Objects.isNull(user)) {
            throw new UserDoesNotExistException();
        }

        user.setWithdraw(true);
        user.setWithdrawedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    /**
     * 주어진 사용자 ID에 대해 사용자의 계정을 비활성화하거나 활성화합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @throws UserDoesNotExistException 사용자가 존재하지 않을 경우 발생합니다.
     */
    @Override
    public void disableOrEnableUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (Objects.isNull(user)) {
            throw new UserDoesNotExistException();
        }
        
        if (user.getEnabled()) {
            user.setEnabled(false);
        }else{
            user.setEnabled(true);
        }
        userRepository.save(user);
    }

    /**
     * 주어진 사용자 ID의 비밀번호를 업데이트합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @param updatePasswordRequestDto 비밀번호 업데이트 요청 DTO
     * @throws UserDoesNotExistException 사용자가 존재하지 않을 경우 발생합니다.
     * @throws IncorrectPassword 비밀번호가 일치하지 않을 경우 발생합니다.
     */
    @Override
    public void updatePassword(String userId, UpdatePasswordRequestDto updatePasswordRequestDto) {
        User user = userRepository.findById(userId).orElse(null);

        if (Objects.isNull(user)) {
            throw new UserDoesNotExistException();
        }else if (passwordEncoder.matches(updatePasswordRequestDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatePasswordRequestDto.getNewPassword()));
            user.setPasswordChangeDate(LocalDateTime.now());
            userRepository.save(user);
        }else{
            throw new IncorrectPassword();
        }
        
    }

    /**
     * 주어진 사용자 ID의 닉네임을 변경합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @param newNickname 새 닉네임
     * @throws UserDoesNotExistException 사용자가 존재하지 않을 경우 발생합니다.
     */
    @Override
    public void changeNickname(String userId, String newNickname) {
        User user = userRepository.findById(userId).orElse(null);

        if (Objects.isNull(user)) {
            throw new UserDoesNotExistException();
        }
        
        user.setNickname(newNickname);
        userRepository.save(user);
    }

    /**
     * 주어진 사용자 ID의 권한을 변경합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @param authorityId 새로운 권한 ID
     * @throws UserDoesNotExistException 사용자 역할이 존재하지 않을 경우 발생합니다.
     */
    @Override
    public void changeAuthority(String userId, Short authorityId) {
        UserRole userRole = userRoleRepository.findByUserId(userId).orElse(null);

        if (Objects.isNull(userRole)) {
            throw new UserDoesNotExistException();
        }
        
        userRole.setAuthorityId(authorityId);;
        userRoleRepository.save(userRole);
    }

    /**
     * 주어진 사용자 ID의 닉네임을 반환합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @return 사용자의 닉네임
     * @throws UserDoesNotExistException 사용자가 존재하지 않을 경우 발생합니다.
     */
    @Override
    public String getUserNickname(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserDoesNotExistException()).getNickname();
    }

}