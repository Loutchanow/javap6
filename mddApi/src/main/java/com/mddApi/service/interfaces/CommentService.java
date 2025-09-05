package com.mddApi.service.interfaces;
import com.mddApi.dto.CommentDTO;
import java.security.Principal;

public interface CommentService {

     void createComment(CommentDTO dto, Principal principal);
}
