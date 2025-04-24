package com.kora.koramonoapi.History.resource;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateHistoryRecordResource {
    private Integer id;
    private Integer patientId;
    private Integer techniqueId;
    private String emotion;
}
