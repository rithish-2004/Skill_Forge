package com.skillforge.compliance.service;

import com.skillforge.compliance.dto.ComplianceRecordRequest;
import com.skillforge.compliance.dto.ComplianceRecordResponse;
import java.util.List;

public interface ComplianceRecordService {
  ComplianceRecordResponse create(ComplianceRecordRequest request);

  ComplianceRecordResponse getById(String complianceId);

  List<ComplianceRecordResponse> getAll();

  ComplianceRecordResponse update(String complianceId, ComplianceRecordRequest request);

  void delete(String complianceId);
}
