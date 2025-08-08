package com.mddApi.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users author;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime subscribedAt = LocalDateTime.now();
}