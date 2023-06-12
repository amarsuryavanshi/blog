package com.Blog2.Controller;

import com.Blog2.Payload.CommentDto;
import com.Blog2.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable("postId")long postId) {
        CommentDto dto = commentService.saveComment(commentDto,postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @GetMapping ("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }
    @GetMapping ("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto>getCommentById(@PathVariable Long postId,@PathVariable Long commentId){
        CommentDto dto = commentService.getCommentById(postId, commentId);
        return  new  ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto>updateComment(@PathVariable("postId") Long postId,
                                                   @PathVariable("commentId") Long commentId,
                                                   @RequestBody CommentDto commentDto){
       CommentDto dto=commentService.updateComment(postId,commentId,commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String>deleteComment(@PathVariable("postId") Long postId,
                                               @PathVariable("commentId") Long commentId){
        commentService.deleteComment(postId,commentId);

        return new ResponseEntity<>("Comment Is Deleted",HttpStatus.OK);
    }

}
