package com.kora.koramonoapi.PersonalizedTechniques.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "techniques_steps")
public class TechniqueDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @JoinColumn(name = "technique_id", nullable = false)
    private Integer techniqueId;

    @Column(length = 2083)
    private String url; // Only for music therapy and mindfulness

    @Column(name = "step_number")
    private Integer stepNumber; // Only for breathing techniques

    @Column
    private String instruction; // Only for breathing techniques

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
