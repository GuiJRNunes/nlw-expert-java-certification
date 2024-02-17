package com.github.guijrnunes.certification_nlw.modules.students.useCases;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.guijrnunes.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.github.guijrnunes.certification_nlw.modules.questions.entities.QuestionEntity;
import com.github.guijrnunes.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.github.guijrnunes.certification_nlw.modules.students.dto.HasCertificationDTO;
import com.github.guijrnunes.certification_nlw.modules.students.dto.StudentCertificationDTO;
import com.github.guijrnunes.certification_nlw.modules.students.entities.AnswersCertificationEntity;
import com.github.guijrnunes.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.github.guijrnunes.certification_nlw.modules.students.entities.StudentEntity;
import com.github.guijrnunes.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import com.github.guijrnunes.certification_nlw.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswerUseCase {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private HasCertificationUseCase hasCertificationUseCase;

    public CertificationStudentEntity execute(@RequestBody StudentCertificationDTO dto) throws Exception {

        // Validation
        var hasCertification = this.hasCertificationUseCase
                .execute(new HasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if (hasCertification) {
            throw new Exception("This student already has this certification!");
        }

        // Generate the answer list
        var questions = questionRepository.findByTechnology(dto.getTechnology());
        var answerEntityList = new ArrayList<AnswersCertificationEntity>();
        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getAnswers().stream().forEach(answerDTO -> {
            QuestionEntity currentQuestion = questions.stream()
                    .filter(q -> q.getId().equals(answerDTO.getQuestionId())).findFirst().get();

            AlternativesEntity correctAlternative = currentQuestion.getAlternatives().stream()
                    .filter(a -> a.isCorrect()).findFirst().get();

            answerDTO.setCorrect(answerDTO.getAnswerId().equals(correctAlternative.getId()));
            if (answerDTO.isCorrect()) {
                correctAnswers.incrementAndGet();
            }

            var answerEntity = AnswersCertificationEntity.builder()
                    .questionId(answerDTO.getQuestionId())
                    .alternativeId(answerDTO.getAnswerId())
                    .isCorrect(answerDTO.isCorrect())
                    .build();
            answerEntityList.add(answerEntity);
        });

        // Get or Create the user
        var studentList = this.studentRepository.findByEmail(dto.getEmail());

        StudentEntity student;
        if (studentList.isEmpty()) {
            student = new StudentEntity();
            student.setEmail(dto.getEmail());
            student = this.studentRepository.save(student);
        } else {
            student = studentList.get(0);
        }

        // Create the Certification
        var certificationEntity = new CertificationStudentEntity();
        certificationEntity.setTechnology(dto.getTechnology());
        certificationEntity.setGrade(correctAnswers.get());
        certificationEntity.setStudentId(student.getId());

        certificationEntity = this.certificationStudentRepository.save(certificationEntity);

        for (AnswersCertificationEntity answerEntity : answerEntityList) {
            answerEntity.setCertificationId(certificationEntity.getId());
            answerEntity.setCertificationStudentEntity(certificationEntity);
            answerEntity.setStudentId(student.getId());
        }

        certificationEntity.setAnswersCertificationEntities(answerEntityList);
        this.certificationStudentRepository.save(certificationEntity);

        return certificationEntity;
    }

}
