package com.skillforge.compliance.controller;

import com.skillforge.compliance.dto.ComplianceRecordRequest;
import com.skillforge.compliance.dto.ComplianceRecordResponse;
import com.skillforge.compliance.entity.ComplianceRecord;
import com.skillforge.compliance.service.ComplianceRecordService;
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
@RequestMapping("/api/v1/compliance-records")
@RequiredArgsConstructor
public class ComplianceRecordController {
  private final ComplianceRecordService complianceRecordService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public ComplianceRecordResponse create(@Valid @RequestBody ComplianceRecord request) {
    return complianceRecordService.create(request);
  }

  @GetMapping("/{complianceId}")
  @PreAuthorize("isAuthenticated()")
  public ComplianceRecordResponse getById(@PathVariable String complianceId) {
    return complianceRecordService.getById(complianceId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<ComplianceRecordResponse> getAll() {
    return complianceRecordService.getAll();
  }

  @PutMapping("/{complianceId}")
  @PreAuthorize("isAuthenticated()")
  public ComplianceRecordResponse update(
      @PathVariable String complianceId, @Valid @RequestBody ComplianceRecordRequest request) {
    return complianceRecordService.update(complianceId, request);
  }

  @DeleteMapping("/{complianceId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String complianceId) {
    complianceRecordService.delete(complianceId);
  }
}
