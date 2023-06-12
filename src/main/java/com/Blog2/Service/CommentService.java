package com.Blog2.Service;

import com.Blog2.Payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(CommentDto commentDto, long postId);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId,long commentId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);

    void deleteComment(Long postId, Long commentId);
}
