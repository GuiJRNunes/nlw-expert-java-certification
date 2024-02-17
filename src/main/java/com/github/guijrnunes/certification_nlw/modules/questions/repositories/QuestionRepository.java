package com.github.guijrnunes.certification_nlw.modules.questions.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.guijrnunes.certification_nlw.modules.questions.entities.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
    
    public List<QuestionEntity> findByTechnology(String techonology);

}
