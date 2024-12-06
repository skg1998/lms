package com.lms.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.api.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
