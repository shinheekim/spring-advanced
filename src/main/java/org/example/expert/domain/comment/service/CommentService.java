package org.example.expert.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.dto.request.CommentSaveRequest;
import org.example.expert.domain.comment.dto.response.CommentResponse;
import org.example.expert.domain.comment.dto.response.CommentSaveResponse;
import org.example.expert.domain.comment.entity.Comment;
import org.example.expert.domain.comment.repository.CommentRepository;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentSaveResponse createComment(AuthUser authUser, long todoId, CommentSaveRequest commentSaveRequest) {
        User user = User.fromAuthUser(authUser.id(), authUser.email(), authUser.userRole());
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new InvalidRequestException("Todo not found"));

        Comment newComment = new Comment(
                commentSaveRequest.contents(),
                user,
                todo
        );

        Comment savedComment = commentRepository.save(newComment);
        return CommentSaveResponse.from(savedComment);
    }

    public List<CommentResponse> retrieveComments(long todoId) {
        List<Comment> commentList = commentRepository.findByTodoIdWithUser(todoId);

        return commentList.stream()
                .map(CommentResponse::from)
                .toList();
    }
}
