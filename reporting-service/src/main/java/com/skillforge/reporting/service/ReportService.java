package com.skillforge.reporting.service;

import com.skillforge.reporting.dto.ReportRequest;
import com.skillforge.reporting.dto.ReportResponse;
import java.util.List;

public interface ReportService {
  ReportResponse create(ReportRequest request);

  ReportResponse getById(String reportId);

  List<ReportResponse> getAll();

  ReportResponse update(String reportId, ReportRequest request);

  void delete(String reportId);
}
