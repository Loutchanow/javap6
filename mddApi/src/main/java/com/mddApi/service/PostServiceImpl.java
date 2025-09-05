package com.mddApi.service;

import com.mddApi.dto.PostDTO;
import com.mddApi.dto.PostResponseDTO;
import com.mddApi.model.Post;
import com.mddApi.model.Subject;
import com.mddApi.model.Users;
import com.mddApi.repository.PostRepository;
import com.mddApi.repository.SubjectRepository;
import com.mddApi.repository.UsersRepository;
import com.mddApi.service.interfaces.PostService;
import com.mddApi.service.mapper.PostMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private PostMapper postMapper;
    
    @Override
    public List<PostResponseDTO> getAllPost(Principal principal) {
    	
        Users user = usersRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Long> sujects = user.getSubscriptions().stream().map(subscription->subscription.getSubject()
        		.getId()
        		).collect(Collectors.toList());
        
       List<Post> posts = postRepository.findBySubjectIdIn(sujects);
       	return postMapper.toListPostResponseDTO(posts);
    }

    @Override
    public Optional<PostResponseDTO> getPostByIdDTO(Long id) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isPresent()) {
            return Optional.of(postMapper.toResponseDto(postOpt.get()));
        }
        return Optional.empty();
    }

    
    @Override
    public Map<String, String> createPost(Principal principal, PostDTO postDTO) {

        Users user = usersRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

 
        Subject subject = subjectRepository.findByName(postDTO.getSubject())
                .orElseThrow(() -> new RuntimeException("Subject not found"));


        Post post = postMapper.toEntity(postDTO);
        post.setUser(user);
        post.setSubject(subject);
        post.setCreatedAt(LocalDateTime.now());

        postRepository.save(post);

        return Map.of("message", "Post created successfully");
    }
    
    @Override
    public void updatePost(Long id, PostDTO dto) {
        Post existing = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Subject subject = subjectRepository.findByName(dto.getSubject())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        existing.setTitle(dto.getTitle());
        existing.setContent(dto.getContent());
        existing.setSubject(subject);

        postRepository.save(existing);
    }
}
