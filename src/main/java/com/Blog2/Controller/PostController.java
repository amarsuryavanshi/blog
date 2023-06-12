package com.Blog2.Controller;

import com.Blog2.Payload.PostDto;
import com.Blog2.Payload.PostResponse;
import com.Blog2.Service.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
   private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?>createPost(@Valid @RequestBody PostDto postDto, BindingResult result){

        if (result.hasErrors()){
return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value="PageNo",defaultValue = "0",required = false)int PageNo,
                                    @RequestParam(value="PageNo",defaultValue = "3",required = false)int PageSize,
                                    @RequestParam(value="sortBy",defaultValue = "id",required = false)String sortBy,
                                    @RequestParam(value="sortDir",defaultValue = "asc",required = false)String sortDir
    ){

        PostResponse postResponse= postService.getAllPosts( PageNo,PageSize,sortBy, sortDir);

        return postResponse;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable("id")long id){
        PostDto dto=postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable("id")long id){
        PostDto dto=postService.updatePost(postDto,id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
   @DeleteMapping ("/{id}")
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>deletePost(@PathVariable("id")long id){
        postService.deletePost(id);

       return new ResponseEntity<>("Post Is Deleted",HttpStatus.OK);
   }

}
