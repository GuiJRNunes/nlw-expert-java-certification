package com.github.guijrnunes.certification_nlw.modules.certifications.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleCertificationDTO {
    
    private UUID id;
    private String technology;
    private int grade;
    private UUID studentId;
    private LocalDateTime createdAt;

}
