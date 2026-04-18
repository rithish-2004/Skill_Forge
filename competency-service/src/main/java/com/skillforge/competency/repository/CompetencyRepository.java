package com.skillforge.competency.repository;

import com.skillforge.competency.entity.Competency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetencyRepository extends JpaRepository<Competency, String> {}
