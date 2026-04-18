package com.skillforge.compliance.repository;

import com.skillforge.compliance.entity.ComplianceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplianceRecordRepository extends JpaRepository<ComplianceRecord, String> {}
