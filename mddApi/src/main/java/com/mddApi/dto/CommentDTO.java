package com.mddApi.dto;

import com.mddApi.model.Post;
import com.mddApi.model.Users;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Users author;
    private Post post;
    private String content;
}
