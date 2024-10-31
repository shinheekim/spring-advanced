package org.example.expert.domain.manager.controller;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.config.JwtUtil;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.manager.dto.request.ManagerSaveRequest;
import org.example.expert.domain.manager.dto.response.ManagerResponse;
import org.example.expert.domain.manager.dto.response.ManagerSaveResponse;
import org.example.expert.domain.manager.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos/{todoId}/managers")
public class ManagerController {

    private final ManagerService managerService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<ManagerSaveResponse> createManager(
            @Auth AuthUser authUser,
            @PathVariable long todoId,
            @Valid @RequestBody ManagerSaveRequest managerSaveRequest
    ) {
        ManagerSaveResponse response = managerService.createManager(authUser, todoId, managerSaveRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ManagerResponse>> retrieveAllManagers(@PathVariable long todoId) {
        List<ManagerResponse> response = managerService.retrieveAllManagers(todoId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{managerId}")
    public ResponseEntity<String> deleteManager(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable long todoId,
            @PathVariable long managerId
    ) {
        Claims claims = jwtUtil.extractClaims(bearerToken.substring(7));
        long userId = Long.parseLong(claims.getSubject());
        managerService.deleteManager(userId, todoId, managerId);
        return ResponseEntity.noContent().build();
    }
}
