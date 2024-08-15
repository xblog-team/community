package com.xblog.community.user.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xblog.community.user.dto.LoginInfoResponseDto;
import com.xblog.community.user.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<LoginInfoResponseDto> getUser(@PathVariable("userId") String userId) {
        LoginInfoResponseDto user = userService.getLoginInfo(userId);

        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
}
