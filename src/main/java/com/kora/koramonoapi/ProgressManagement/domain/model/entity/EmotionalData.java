package com.kora.koramonoapi.ProgressManagement.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emotional_data")
public class EmotionalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "patient_id")
    private Integer patientId;

    private String emotion;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
