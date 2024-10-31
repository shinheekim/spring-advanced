package org.example.expert.domain.todo.dto.response;

import lombok.Builder;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.dto.response.UserResponse;

import java.time.LocalDateTime;

@Builder
public record TodoResponse(
        Long id,
        String title,
        String contents,
        String weather,
        UserResponse user,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static TodoResponse from(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .contents(todo.getContents())
                .weather(todo.getWeather())
                .user(UserResponse.from(todo.getUser()))
                .createdAt(todo.getCreatedAt())
                .modifiedAt(todo.getModifiedAt())
                .build();
    }
}
