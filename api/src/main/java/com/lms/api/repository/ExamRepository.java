package com.lms.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.api.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>{

}
