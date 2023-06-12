package com.Blog2.Payload;

import lombok.Data;

import java.util.List;
@Data
public class PostResponse {
    private List<PostDto> content;
    private  int pageNo;
    private int pageSize;
    private int totalPages;
    private  long totalElement;
    private  boolean isLast;
}

