package com.skillforge.assessment.controller;

import com.skillforge.assessment.dto.CertificationRequest;
import com.skillforge.assessment.dto.CertificationResponse;
import com.skillforge.assessment.service.CertificationService;
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
@RequestMapping("/api/v1/certifications")
@RequiredArgsConstructor
public class CertificationController {
  private final CertificationService certificationService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public CertificationResponse create(@Valid @RequestBody CertificationRequest request) {
    return certificationService.create(request);
  }

  @GetMapping("/{certificationId}")
  @PreAuthorize("isAuthenticated()")
  public CertificationResponse getById(@PathVariable String certificationId) {
    return certificationService.getById(certificationId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<CertificationResponse> getAll() {
    return certificationService.getAll();
  }

  @PutMapping("/{certificationId}")
  @PreAuthorize("isAuthenticated()")
  public CertificationResponse update(
      @PathVariable String certificationId, @Valid @RequestBody CertificationRequest request) {
    return certificationService.update(certificationId, request);
  }

  @DeleteMapping("/{certificationId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String certificationId) {
    certificationService.delete(certificationId);
  }
}
