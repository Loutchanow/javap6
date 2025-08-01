package com.mddApi.service.mapper;

import com.mddApi.dto.PostDTO;
import com.mddApi.model.Post;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface PostMapper {
    Post toEntity(PostDTO postDTO);
    PostDTO toDto(Post post);

    void copy(PostDTO postDTO, @MappingTarget Post post);

}
