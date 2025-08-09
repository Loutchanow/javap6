package com.mddApi.dto;

import com.mddApi.model.Post;
import com.mddApi.model.Users;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Users user;
    private Post post;
    private String content;
    private Long postId;
}
