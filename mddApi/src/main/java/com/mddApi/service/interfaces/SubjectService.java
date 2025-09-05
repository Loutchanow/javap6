package com.mddApi.service.interfaces;
import com.mddApi.dto.SubjectDTO;
import java.util.List;
import java.util.Optional;


public interface SubjectService  {


    List<SubjectDTO> getAllSubjects();

    Optional<SubjectDTO> getSubjectById(Long id);

    SubjectDTO createSubject(SubjectDTO dto);

    Optional<SubjectDTO> updateSubject(Long id, SubjectDTO dto);

    boolean deleteSubject(Long id);
}

