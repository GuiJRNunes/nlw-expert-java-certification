package com.github.guijrnunes.certification_nlw.modules.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.guijrnunes.certification_nlw.modules.students.entities.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    
    public List<StudentEntity> findByEmail(String email);

}
