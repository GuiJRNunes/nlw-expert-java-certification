package com.github.guijrnunes.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.guijrnunes.certification_nlw.modules.students.dto.HasCertificationDTO;
import com.github.guijrnunes.certification_nlw.modules.students.dto.StudentCertificationDTO;
import com.github.guijrnunes.certification_nlw.modules.students.useCases.HasCertificationUseCase;
import com.github.guijrnunes.certification_nlw.modules.students.useCases.StudentCertificationAnswerUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private HasCertificationUseCase hasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswerUseCase studentCertificationAnswerUseCase;

    @GetMapping("/hasCertification")
    public String getHasCertification(@RequestBody HasCertificationDTO hasCertificationDTO) {
        if (this.hasCertificationUseCase.execute(hasCertificationDTO)) {
            return "This student has the certification for this technology!";
        }
        
        return "This student does not have the certification for this technology!";
    }

    @PostMapping("/certification/answer")    
    public ResponseEntity<Object> postCertificationAnswer(@RequestBody StudentCertificationDTO dto) {
        try {
            var result = this.studentCertificationAnswerUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    } 

}
