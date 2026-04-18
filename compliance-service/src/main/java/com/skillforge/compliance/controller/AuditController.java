package com.skillforge.compliance.controller;

import com.skillforge.compliance.dto.AuditRequest;
import com.skillforge.compliance.dto.AuditResponse;
import com.skillforge.compliance.service.AuditService;
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
@RequestMapping("/api/v1/audits")
@RequiredArgsConstructor
public class AuditController {
  private final AuditService auditService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public AuditResponse create(@Valid @RequestBody AuditRequest request) {
    return auditService.create(request);
  }

  @GetMapping("/{auditId}")
  @PreAuthorize("isAuthenticated()")
  public AuditResponse getById(@PathVariable String auditId) {
    return auditService.getById(auditId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<AuditResponse> getAll() {
    return auditService.getAll();
  }

  @PutMapping("/{auditId}")
  @PreAuthorize("isAuthenticated()")
  public AuditResponse update(@PathVariable String auditId, @Valid @RequestBody AuditRequest request) {
    return auditService.update(auditId, request);
  }

  @DeleteMapping("/{auditId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String auditId) {
    auditService.delete(auditId);
  }
}
