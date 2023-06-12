package com.Blog2.Service.Impl;

import com.Blog2.Entity.Post;
import com.Blog2.Exception.ResourceNotFoundException;
import com.Blog2.Payload.PostDto;
import com.Blog2.Payload.PostResponse;
import com.Blog2.Repository.PostRepository;
import com.Blog2.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
     private ModelMapper mapper;
    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {

        this.postRepository = postRepository;
        this.mapper=mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapTOEntity(postDto);

        Post savedPost = postRepository.save(post);
        PostDto dto=mapToDto(savedPost);

        return dto;
    }



    @Override
    public PostResponse getAllPosts(int PageNo,int PageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(PageNo, PageSize, sort);
        Page<Post> content = postRepository.findAll(pageable);
        List<Post> posts = content.getContent();
        List<PostDto> Dtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse =new PostResponse();
        postResponse.setContent(Dtos);
        postResponse.setPageNo(content.getNumber());
        postResponse.setPageSize(content.getSize());
        postResponse.setTotalPages(content.getTotalPages());
        postResponse.setTotalElement(content.getTotalElements());
        postResponse.setLast(content.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post not found with id: "+id)
        );
        PostDto postDto = mapToDto(post);
        return postDto;
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post=postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post not found with id: "+id)
        );
        post.setName(postDto.getName());
        post.setEmail(postDto.getEmail());
        post.setMobile(postDto.getMobile());
        Post updatePost = postRepository.save(post);
        return mapToDto(updatePost);
    }

    @Override
    public void deletePost(long id) {
        postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post not found with id: "+id)
        );
        postRepository.deleteById(id);

    }


    Post mapTOEntity(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);
//       Post post = new Post();
//        post.setName(postDto.getName());
//        post.setEmail(postDto.getEmail());
    //    post.setMobile(postDto.getMobile());
       return post;
    }

    PostDto mapToDto(Post post) {

        PostDto dto = mapper.map(post, PostDto.class);
//        PostDto dto = new PostDto();
//        dto.setName(post.getName());
//        dto.setEmail(post.getEmail());
//        dto.setMobile(post.getMobile());
        return dto;
    }

}
