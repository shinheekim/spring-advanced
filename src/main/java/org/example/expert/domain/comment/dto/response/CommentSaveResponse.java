package org.example.expert.domain.comment.dto.response;

import lombok.Builder;
import org.example.expert.domain.comment.entity.Comment;
import org.example.expert.domain.user.dto.response.UserResponse;

@Builder
public record CommentSaveResponse(
        Long id,
        String contents,
        UserResponse user
) {
    public static CommentSaveResponse from(Comment comment) {
        return CommentSaveResponse.builder()
                .id(comment.getId())
                .contents(comment.getContents())
                .user(new UserResponse(comment.getUser().getId(), comment.getUser().getEmail()))
                .build();
    }
}
