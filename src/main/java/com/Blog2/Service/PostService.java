package com.Blog2.Service;

import com.Blog2.Payload.PostDto;
import com.Blog2.Payload.PostResponse;

import java.util.List;

public interface PostService {
PostDto  createPost(PostDto postDto);

    PostResponse getAllPosts(int PageNo, int PageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);
}
