package org.example.expert.domain.manager.dto.response;

import lombok.Builder;
import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.user.dto.response.UserResponse;
@Builder
public record ManagerResponse(
        Long id,
        UserResponse user
) {
    public static ManagerResponse from(Manager manager) {
        return ManagerResponse.builder()
                .id(manager.getId())
                .user(UserResponse.from(manager.getUser()))
                .build();
    }
}
