package com.github.guijrnunes.certification_nlw.modules.certifications.useCases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.guijrnunes.certification_nlw.modules.certifications.dto.SimpleCertificationDTO;
import com.github.guijrnunes.certification_nlw.modules.students.repositories.CertificationStudentRepository;

@Service
public class Top10RankingUseCase {
    
    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    /* FIXME : For testing purposes - Full entities */
    /* public List<CertificationStudentEntity> execute() {
        return this.certificationStudentRepository.findTop10ByOrderByGradeDesc();
    } */

    public List<SimpleCertificationDTO> execute() {
        var certifications = this.certificationStudentRepository.findTop10ByOrderByGradeDesc();

        var top10RankingList = new ArrayList<SimpleCertificationDTO>();
        certifications.forEach((certification) -> {
            top10RankingList.add(
                SimpleCertificationDTO.builder()
                    .id(certification.getId())
                    .technology(certification.getTechnology())
                    .grade(certification.getGrade())
                    .studentId(certification.getStudentId())
                    .createdAt(certification.getCreatedAt())
                .build()
            );
        });

        return top10RankingList;
    }

}
