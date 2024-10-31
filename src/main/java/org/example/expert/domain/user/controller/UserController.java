package org.example.expert.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> retrieveUser(@PathVariable long userId) {
        UserResponse response = userService.retrieveUser(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<String> changePassword(@Auth AuthUser authUser,
                               @RequestBody UserChangePasswordRequest userChangePasswordRequest) {
        userService.changePassword(authUser.id(), userChangePasswordRequest);
        return ResponseEntity.ok().build();
    }
}
