package com.mddApi.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String email;
    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime created_at;


}