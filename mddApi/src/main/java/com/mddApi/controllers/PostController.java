package com.mddApi.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mddApi.dto.PostDTO;
import com.mddApi.dto.PostResponseDTO;
import com.mddApi.service.interfaces.PostService;


@RestController
@RequestMapping("/api/posts")
public class PostController {


	    private final PostService postService;
	    
	    

	    public PostController(PostService postService) {
	        this.postService = postService ;
	    }

	    @GetMapping
	    public List<PostResponseDTO> getAllPost(Principal principal) {
	        List<PostResponseDTO> post = postService.getAllPost(principal);
	        
//	        Map<String, List<PostResponseDTO>> response = new HashMap<>();
	        return post;
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<PostResponseDTO> getRental(@PathVariable Long id) {
	        return postService.getPostByIdDTO(id)
	                .map(dto -> ResponseEntity.ok(dto))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping("")
	    public ResponseEntity<Map<String, String>> createPost(
	            @ModelAttribute PostDTO postDTO,
	            Principal principal
	    ){
	    	return ResponseEntity.status(HttpStatus.CREATED)
	                .body(postService.createPost(principal, postDTO));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Map<String, String>> updatePost(
	            @PathVariable Long id,
	            @RequestParam("title") String title,
	            @RequestParam("content") String content,
	            @RequestParam("description") String description,
	            @RequestParam("subject") String subject
	    ) {
	    	
	        PostDTO dto = new PostDTO();

	        dto.setTitle(title);
	        dto.setContent(content);
	        dto.setSubject(subject);

	        postService.updatePost(id, dto);

	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Post updated !");
	        return ResponseEntity.ok(response);
	    }
}
