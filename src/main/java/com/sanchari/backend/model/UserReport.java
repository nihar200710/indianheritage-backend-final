package com.sanchari.backend.model;

import jakarta.persistence.*; // This fixes @Table, @Entity, @Id, @GeneratedValue, @ManyToOne, @JoinColumn
import lombok.*;            // This fixes @Data, @NoArgsConstructor, @AllArgsConstructor, @Builder
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_reports") // Changed from forum_threads to avoid database confusion
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String severity;
    private String title;
    private String description;
    private String actionLabel;
    private String resolvedLabel;

    @Builder.Default
    private String status = "PENDING";

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}