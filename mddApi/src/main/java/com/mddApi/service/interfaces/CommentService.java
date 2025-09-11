package com.mddApi.service.interfaces;
import com.mddApi.dto.CommentDTO;
import java.security.Principal;
import java.util.List;

public interface CommentService {

     void createComment(CommentDTO dto, Principal principal);
     
     List<CommentDTO> getCommentsByPost(Long postId);

}
