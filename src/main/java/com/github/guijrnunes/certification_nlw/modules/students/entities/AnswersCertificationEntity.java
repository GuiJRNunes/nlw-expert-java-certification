package com.github.guijrnunes.certification_nlw.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.guijrnunes.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.github.guijrnunes.certification_nlw.modules.questions.entities.QuestionEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "answers")
public class AnswersCertificationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "certification_id")
    private UUID certificationId;

    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    @JsonBackReference
    private CertificationStudentEntity certificationStudentEntity;

    @Column(name = "student_id")
    private UUID studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    @Column(name = "question_id")
    private UUID questionId;

    @ManyToOne
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private QuestionEntity questionEntity;

    @Column(name = "alternative_id")
    private UUID alternativeId;

    @ManyToOne
    @JoinColumn(name = "alternative_id", insertable = false, updatable = false)
    private AlternativesEntity alternativesEntity;

    private boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
