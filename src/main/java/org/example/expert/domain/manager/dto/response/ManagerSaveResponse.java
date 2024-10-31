package org.example.expert.domain.manager.dto.response;

import lombok.Builder;
import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.user.dto.response.UserResponse;

@Builder
public record ManagerSaveResponse(
        Long id,
        UserResponse user
) {

    public static ManagerSaveResponse from(Manager savedManagerUser) {
        return ManagerSaveResponse.builder()
                .id(savedManagerUser.getId())
                .user(UserResponse.from(savedManagerUser.getUser()))
                .build();
    }
}
