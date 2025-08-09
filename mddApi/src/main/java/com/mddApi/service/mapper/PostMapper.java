package com.mddApi.service.mapper;

import com.mddApi.dto.PostDTO;
import com.mddApi.dto.PostResponseDTO;
import com.mddApi.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "user", ignore = true)
    Post toEntity(PostDTO dto);

    @Mapping(target = "subjectName", source = "subject.name")
    @Mapping(target = "userName", source = "user.name")
    PostResponseDTO toResponseDto(Post post);
}
