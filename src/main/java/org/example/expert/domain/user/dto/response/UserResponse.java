package org.example.expert.domain.user.dto.response;

import lombok.Builder;
import org.example.expert.domain.user.entity.User;

@Builder
public record UserResponse(
        Long id,
        String email
) {
    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
