package com.Blog2.Service.Impl;

import com.Blog2.Entity.Comment;
import com.Blog2.Entity.Post;
import com.Blog2.Exception.BlogAPIException;
import com.Blog2.Exception.ResourceNotFoundException;
import com.Blog2.Payload.CommentDto;
import com.Blog2.Repository.CommentRepository;
import com.Blog2.Repository.PostRepository;
import com.Blog2.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CommentServiceImpl implements CommentService {

    private  CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper=mapper;

    }

    @Override
    public CommentDto saveComment(CommentDto commentDto, long postId) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("post not found with id: "+id)
                );


        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);
        return   mapToDto( newComment);
    }
    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List <Comment >comments = commentRepository.findByPostId(postId);
        return   comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("post not found with id: "+postId)
                );
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("post not found with id: "+commentId)
                );
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException( "Comment does not belong to post");
        }

        return   mapToDto(comment);


    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id: " +postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found with id: " +commentId)
        );
if (!comment.getPost().getId().equals(post.getId())) {
    throw new BlogAPIException("comment does not belong the post");
}
comment.setName(commentDto.getName());
comment.setBody(commentDto.getBody());
comment.setEmail(commentDto.getEmail());

        Comment updateComment = commentRepository.save(comment);

        return mapToDto( updateComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id: " +postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found with id: " +commentId)
        );
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException("comment does not belong the post");
        }
        commentRepository.delete(comment);

    }


    Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);

//        Comment comment=new Comment();
//        comment.setName(commentDto.getName());
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
        return comment;
    }
    CommentDto mapToDto(Comment comment){
        CommentDto Dto = mapper.map(comment, CommentDto.class);
//        CommentDto Dto=new CommentDto();
//        Dto.setId(comment.getId());
//        Dto.setName(comment.getName());
//        Dto.setBody(comment.getBody());
//        Dto.setEmail(comment.getEmail());
        return Dto;
    }

}
