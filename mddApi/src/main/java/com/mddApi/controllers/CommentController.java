 package com.mddApi.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import com.mddApi.dto.CommentDTO;
import com.mddApi.service.interfaces.CommentService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody CommentDTO dto,
            BindingResult bindingResult,
            Principal principal
    ) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(fe ->
                errors.put(fe.getField(), fe.getDefaultMessage()) 
            );
            return ResponseEntity.badRequest().body(errors);
        }

        commentService.createComment(dto, principal); 

        Map<String, String> response = new HashMap<>();
        response.put("comment", "sendd with succes");
        return ResponseEntity.ok(response);
    }

}
