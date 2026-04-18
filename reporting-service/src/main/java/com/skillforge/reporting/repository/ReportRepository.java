package com.skillforge.reporting.repository;

import com.skillforge.reporting.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, String> {}
