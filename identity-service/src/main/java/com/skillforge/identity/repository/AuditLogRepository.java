package com.skillforge.identity.repository;

import com.skillforge.identity.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, String> {}
