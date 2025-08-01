package com.mddApi.service.mapper;

import com.mddApi.dto.SubjectDTO;
import com.mddApi.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface SubjectMapper {
	Subject toEntity(SubjectDTO subjectDTO);
    SubjectDTO toDto(Subject subject);

    void copy(SubjectDTO subjectDTO, @MappingTarget Subject subject);

}
