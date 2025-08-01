package com.mddApi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionDTO {
    private Long id;
    private LocalDateTime subscribedAt;
    private Long userId;
    private Long subjectId;
}
