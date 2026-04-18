package com.skillforge.reporting.controller;

import com.skillforge.reporting.dto.ReportRequest;
import com.skillforge.reporting.dto.ReportResponse;
import com.skillforge.reporting.service.ReportService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
  private final ReportService reportService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public ReportResponse create(@Valid @RequestBody ReportRequest request) {
    return reportService.create(request);
  }

  @GetMapping("/{reportId}")
  @PreAuthorize("isAuthenticated()")
  public ReportResponse getById(@PathVariable String reportId) {
    return reportService.getById(reportId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<ReportResponse> getAll() {
    return reportService.getAll();
  }

  @PutMapping("/{reportId}")
  @PreAuthorize("isAuthenticated()")
  public ReportResponse update(@PathVariable String reportId, @Valid @RequestBody ReportRequest request) {
    return reportService.update(reportId, request);
  }

  @DeleteMapping("/{reportId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String reportId) {
    reportService.delete(reportId);
  }
}
