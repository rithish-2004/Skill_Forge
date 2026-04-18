package com.skillforge.compliance.service;

import com.skillforge.compliance.dto.AuditRequest;
import com.skillforge.compliance.dto.AuditResponse;
import java.util.List;

public interface AuditService {
  AuditResponse create(AuditRequest request);

  AuditResponse getById(String auditId);

  List<AuditResponse> getAll();

  AuditResponse update(String auditId, AuditRequest request);

  void delete(String auditId);
}
