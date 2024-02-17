package com.github.guijrnunes.certification_nlw.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HasCertificationDTO {
    
    private String email;
    private String technology;

}
