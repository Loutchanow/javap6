package com.mddApi.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String subjectName;
    private String userName;
    private LocalDateTime createdAt;
}
