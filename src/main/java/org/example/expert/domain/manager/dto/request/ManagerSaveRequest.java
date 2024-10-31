package org.example.expert.domain.manager.dto.request;

import jakarta.validation.constraints.NotNull;

public record ManagerSaveRequest(
        @NotNull
        Long managerUserId // 일정 작성자가 배치하는 유저 id
) {
}
