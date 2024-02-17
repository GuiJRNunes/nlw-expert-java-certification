package com.github.guijrnunes.certification_nlw.modules.certifications.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.guijrnunes.certification_nlw.modules.certifications.dto.SimpleCertificationDTO;
import com.github.guijrnunes.certification_nlw.modules.certifications.useCases.Top10RankingUseCase;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    
    @Autowired
    private Top10RankingUseCase top10RankingUseCase;

    @GetMapping("/top10")
    /* public List<CertificationStudentEntity> getTop10() { // FIXME: For testing purposes - Full entities */
    public List<SimpleCertificationDTO> getTop10() {
        return this.top10RankingUseCase.execute();
    }

}
