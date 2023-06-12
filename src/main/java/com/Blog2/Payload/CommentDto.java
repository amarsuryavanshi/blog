package com.Blog2.Payload;

import lombok.Data;

@Data
public class CommentDto {
    private  Long id;
    private String name;
    private String body;
    private String email;
}
