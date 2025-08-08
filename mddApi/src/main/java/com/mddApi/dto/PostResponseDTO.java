package com.mddApi.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostResponseDTO {
	   private Long id;
	    private String title;
	    private String content;
	    private String subject;
	    private Long user;
	    private String created_at;
}
