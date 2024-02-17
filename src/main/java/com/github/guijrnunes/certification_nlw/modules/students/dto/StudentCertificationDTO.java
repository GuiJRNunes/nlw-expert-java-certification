package com.github.guijrnunes.certification_nlw.modules.students.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCertificationDTO {
    
    private String email;
    private String technology;
    private List<QuestionAnswerDTO> answers;

}
