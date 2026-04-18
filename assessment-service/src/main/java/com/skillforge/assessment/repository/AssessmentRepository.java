package com.skillforge.assessment.repository;

import com.skillforge.assessment.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, String> {}
