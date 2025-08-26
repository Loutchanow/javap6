package com.mddApi.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime created_at;
    
}