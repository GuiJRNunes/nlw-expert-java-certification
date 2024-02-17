package com.github.guijrnunes.certification_nlw.modules.questions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.guijrnunes.certification_nlw.modules.questions.dto.AlternativesResultDTO;
import com.github.guijrnunes.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.github.guijrnunes.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.github.guijrnunes.certification_nlw.modules.questions.entities.QuestionEntity;
import com.github.guijrnunes.certification_nlw.modules.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    
    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        List<QuestionEntity> questions = this.questionRepository.findByTechnology(technology);

        return questions.stream().map(question -> mapQuestionToDTO(question)).toList();
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        var result = QuestionResultDTO.builder()
            .id(question.getId())
            .description(question.getDescription())
            .technology(question.getTechnology())
            .build();

        List<AlternativesResultDTO> alternatives = question.getAlternatives().stream().map(alternative -> mapAlternativeToDTO(alternative)).toList();

        result.setAlternatives(alternatives);
        return result;
    }

    static AlternativesResultDTO mapAlternativeToDTO(AlternativesEntity alternative) {
        return AlternativesResultDTO.builder()
            .id(alternative.getId())
            .description(alternative.getDescription())
            .build();
    }

}
