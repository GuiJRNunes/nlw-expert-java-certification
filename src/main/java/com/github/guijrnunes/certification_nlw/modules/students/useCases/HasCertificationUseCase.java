package com.github.guijrnunes.certification_nlw.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.guijrnunes.certification_nlw.modules.students.dto.HasCertificationDTO;
import com.github.guijrnunes.certification_nlw.modules.students.repositories.CertificationStudentRepository;

@Service
public class HasCertificationUseCase {
    
    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public boolean execute(HasCertificationDTO hasCertificationDTO) {
        return ! this.certificationStudentRepository
                   .findByStudentEmailAndTechnology(hasCertificationDTO.getEmail(), hasCertificationDTO.getTechnology())
                   .isEmpty();
    }

}
