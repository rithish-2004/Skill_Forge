package com.skillforge.identity.service;

import com.skillforge.identity.dto.AuditLogRequest;
import com.skillforge.identity.dto.AuditLogResponse;
import java.util.List;

public interface AuditLogService {
  AuditLogResponse create(AuditLogRequest request);

  AuditLogResponse getById(String auditId);

  List<AuditLogResponse> getAll();

  AuditLogResponse update(String auditId, AuditLogRequest request);

  void delete(String auditId);
}
