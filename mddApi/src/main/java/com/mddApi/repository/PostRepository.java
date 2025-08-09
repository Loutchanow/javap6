package com.mddApi.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mddApi.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    List<Post> findAll();
//    Optional<Post> findByTitle(String string);
}