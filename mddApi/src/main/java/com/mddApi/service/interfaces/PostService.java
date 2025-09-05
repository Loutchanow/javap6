package com.mddApi.service.interfaces;
import com.mddApi.dto.PostDTO;
import com.mddApi.dto.PostResponseDTO;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface PostService {
	/**
	 * 
	 * @param principal
	 * @return
	 */
     List<PostResponseDTO> getAllPost(Principal principal);

     Optional<PostResponseDTO> getPostByIdDTO(Long id);
  
     Map<String, String> createPost(Principal principal, PostDTO postDTO);

     void updatePost(Long id, PostDTO dto);

}
