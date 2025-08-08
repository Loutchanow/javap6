package com.mddApi.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mddApi.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAll();
    Optional<Subject> findByName(String name);
}