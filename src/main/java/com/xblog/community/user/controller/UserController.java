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
