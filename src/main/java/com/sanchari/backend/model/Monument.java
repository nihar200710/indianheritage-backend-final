package com.sanchari.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "monuments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    // Use @Column(columnDefinition = "TEXT") to match the TEXT type in MySQL
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String history;

    @Column(columnDefinition = "TEXT")
    private String architecture;

    private String mapQuery;
    private String guide;
}