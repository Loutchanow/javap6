
package com.mddApi.service;

import com.mddApi.dto.SubjectDTO;
import com.mddApi.model.Subject;
import com.mddApi.repository.SubjectRepository;
import com.mddApi.service.interfaces.SubjectService;
import com.mddApi.service.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectMapper subjectMapper;

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<SubjectDTO> getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .map(subjectMapper::toDto);
    }

    @Override
    public SubjectDTO createSubject(SubjectDTO dto) {
        Subject subject = subjectMapper.toEntity(dto);
        return subjectMapper.toDto(subjectRepository.save(subject));
    }
    
    @Override
    public Optional<SubjectDTO> updateSubject(Long id, SubjectDTO dto) {
        return subjectRepository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    return subjectMapper.toDto(subjectRepository.save(existing));
                });
    }

    @Override
    public boolean deleteSubject(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
