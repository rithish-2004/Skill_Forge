package com.skillforge.identity.controller;

import com.skillforge.identity.dto.AuditLogRequest;
import com.skillforge.identity.dto.AuditLogResponse;
import com.skillforge.identity.service.AuditLogService;
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
@RequestMapping("/api/v1/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {
  private final AuditLogService auditLogService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAnyRole('ADMIN','HR')")
  public AuditLogResponse create(@Valid @RequestBody AuditLogRequest request) {
    return auditLogService.create(request);
  }

  @GetMapping("/{auditId}")
  @PreAuthorize("isAuthenticated()")
  public AuditLogResponse getById(@PathVariable String auditId) {
    return auditLogService.getById(auditId);
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN','HR')")
  public List<AuditLogResponse> getAll() {
    return auditLogService.getAll();
  }

  @PutMapping("/{auditId}")
  @PreAuthorize("hasAnyRole('ADMIN','HR')")
  public AuditLogResponse update(
      @PathVariable String auditId, @Valid @RequestBody AuditLogRequest request) {
    return auditLogService.update(auditId, request);
  }

  @DeleteMapping("/{auditId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('ADMIN')")
  public void delete(@PathVariable String auditId) {
    auditLogService.delete(auditId);
  }
}
