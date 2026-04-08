package com.sanchari.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "thread_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThreadMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author; // 👈 This was missing or named differently!
    
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "thread_id")
    private ForumThread thread;

    @CreationTimestamp
    private LocalDateTime createdAt;
}