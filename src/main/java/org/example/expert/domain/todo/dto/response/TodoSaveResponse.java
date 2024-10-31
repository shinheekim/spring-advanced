package org.example.expert.domain.todo.dto.response;

import lombok.Builder;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.dto.response.UserResponse;

@Builder
public record TodoSaveResponse(
        Long id,
        String title,
        String contents,
        String weather,
        UserResponse user
) {

    public static TodoSaveResponse from(Todo todo) {
        return TodoSaveResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .contents(todo.getContents())
                .weather(todo.getWeather())
                .user(UserResponse.from(todo.getUser()))
                .build();
    }
}
