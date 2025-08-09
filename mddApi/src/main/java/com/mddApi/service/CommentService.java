package com.mddApi.service;

import com.mddApi.dto.CommentDTO;
import com.mddApi.model.Comment;
import com.mddApi.model.Post;
import com.mddApi.model.Users;
import com.mddApi.repository.CommentRepository;
import com.mddApi.repository.PostRepository;
import com.mddApi.repository.UsersRepository;
import com.mddApi.service.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentMapper commentMapper;

    public void createComment(CommentDTO dto, Principal principal) {
        Users user = usersRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = commentMapper.toEntity(dto);
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }
}
