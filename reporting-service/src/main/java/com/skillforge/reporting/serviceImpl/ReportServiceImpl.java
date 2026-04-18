package com.skillforge.reporting.serviceImpl;

import com.skillforge.reporting.dto.ReportRequest;
import com.skillforge.reporting.dto.ReportResponse;
import com.skillforge.reporting.entity.Report;
import com.skillforge.reporting.exception.ApiException;
import com.skillforge.reporting.repository.ReportRepository;
import com.skillforge.reporting.service.ReportService;
import com.skillforge.reporting.util.IdGenerator;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
  private final ReportRepository reportRepository;

  @Override
  @Transactional
  public ReportResponse create(ReportRequest request) {
    var generated = request.getGeneratedDate() != null ? request.getGeneratedDate() : LocalDateTime.now();
    var entity =
        Report.builder()
            .reportId(IdGenerator.nextReportId())
            .scope(request.getScope())
            .metrics(request.getMetrics())
            .generatedDate(generated)
            .build();
    return map(reportRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public ReportResponse getById(String reportId) {
    return map(find(reportId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReportResponse> getAll() {
    return reportRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public ReportResponse update(String reportId, ReportRequest request) {
    var entity = find(reportId);
    entity.setScope(request.getScope());
    entity.setMetrics(request.getMetrics());
    entity.setGeneratedDate(
        request.getGeneratedDate() != null ? request.getGeneratedDate() : entity.getGeneratedDate());
    return map(reportRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String reportId) {
    if (!reportRepository.existsById(reportId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Report not found");
    }
    reportRepository.deleteById(reportId);
  }

  private Report find(String reportId) {
    return reportRepository
        .findById(reportId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Report not found"));
  }

  private ReportResponse map(Report r) {
    return ReportResponse.builder()
        .reportId(r.getReportId())
        .scope(r.getScope())
        .metrics(r.getMetrics())
        .generatedDate(r.getGeneratedDate())
        .build();
  }
}
