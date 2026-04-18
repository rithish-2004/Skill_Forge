package com.skillforge.assessment.repository;

import com.skillforge.assessment.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, String> {}
