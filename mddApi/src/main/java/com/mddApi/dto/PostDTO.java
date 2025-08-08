package com.mddApi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    private String subject;
    private Long userId;
}
