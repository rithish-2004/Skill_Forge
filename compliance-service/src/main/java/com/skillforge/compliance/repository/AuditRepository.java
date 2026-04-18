package com.skillforge.compliance.repository;

import com.skillforge.compliance.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, String> {}
