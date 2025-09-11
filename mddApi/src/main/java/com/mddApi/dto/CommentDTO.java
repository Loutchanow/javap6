package com.mddApi.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String content;

    private Long postId;
    private String userName; 

    private LocalDateTime createdAt;
}
