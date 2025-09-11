package com.mddApi.service.mapper;
import com.mddApi.dto.CommentDTO;
import com.mddApi.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface CommentMapper {
    Comment toEntity(CommentDTO commentDTO);


    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "post.id", target = "postId")
    CommentDTO toDto(Comment comment);
    
    void copy(CommentDTO commentDTO, @MappingTarget Comment comment);

}
