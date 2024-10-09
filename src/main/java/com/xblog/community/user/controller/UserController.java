package com.xblog.community.user.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xblog.community.user.dto.LoginInfoResponseDto;
import com.xblog.community.user.dto.RegisterUserRequestDto;
import com.xblog.community.user.dto.RegisterUserResponseDto;
import com.xblog.community.user.dto.UpdatePasswordRequestDto;
import com.xblog.community.user.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 사용자 관련 요청을 처리하는 REST 컨트롤러입니다.
 * 이 클래스는 사용자 정보 조회, 등록, 탈퇴, 비활성화 및 활성화, 비밀번호 및 닉네임 변경, 권한 변경과 관련된 API 엔드포인트를 제공합니다.
 * 
 * <p>주요 API 엔드포인트:</p>
 * <ul>
 *   <li>{@link #getUser(String)} - 특정 사용자 ID에 대한 로그인 정보를 조회합니다.</li>
 *   <li>{@link #getNickname(String)} - 특정 사용자 ID에 대한 닉네임을 조회합니다.</li>
 *   <li>{@link #registerUser(RegisterUserRequestDto)} - 새로운 사용자를 등록합니다.</li>
 *   <li>{@link #withdrawUser(String)} - 특정 사용자 ID의 사용자를 탈퇴시킵니다.</li>
 *   <li>{@link #disableOrEnableUser(String)} - 특정 사용자 ID의 사용자 계정을 비활성화 또는 활성화합니다.</li>
 *   <li>{@link #changePassword(String, UpdatePasswordRequestDto)} - 특정 사용자 ID의 비밀번호를 변경합니다.</li>
 *   <li>{@link #changeNickname(String, String)} - 특정 사용자 ID의 닉네임을 변경합니다.</li>
 *   <li>{@link #changeAuth(String, Short)} - 특정 사용자 ID의 권한을 변경합니다.</li>
 * </ul>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<LoginInfoResponseDto> getUser(@PathVariable("userId") String userId) {
        LoginInfoResponseDto user = userService.getLoginInfo(userId);

        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/nickname")
    public ResponseEntity<String> getNickname(@RequestHeader("X-USER-ID") String userId) {
        return ResponseEntity.ok().body(userService.getUserNickname(userId));
    }
    
    
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(registerUserRequestDto));
    }
    
    @PutMapping("/withdraw")
    public ResponseEntity<Void> withdrawUser(@RequestHeader("X-USER-ID") String userId) {
        userService.withdrawUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/disable-or-enable")
    public ResponseEntity<Void> disableOrEnableUser(@RequestHeader("X-USER-ID") String userId) {
        userService.disableOrEnableUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestHeader("X-USER-ID") String userId, @RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
        userService.updatePassword(userId, updatePasswordRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-nickname")
    public ResponseEntity<Void> changeNickname(@RequestHeader("X-USER-ID") String userId, @RequestParam String newNickname) {
        userService.changeNickname(userId, newNickname);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-auth")
    public ResponseEntity<Void> changeAuth(@RequestHeader("X-USER-ID") String userId, @RequestParam Short newAuth) {
        userService.changeAuthority(userId, newAuth);
        return ResponseEntity.ok().build();
    }
}