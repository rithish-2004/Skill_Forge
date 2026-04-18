package com.skillforge.enrollment.controller;

import com.skillforge.enrollment.dto.EnrollmentRequest;
import com.skillforge.enrollment.dto.EnrollmentResponse;
import com.skillforge.enrollment.service.EnrollmentService;
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
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
  private final EnrollmentService enrollmentService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public EnrollmentResponse create(@Valid @RequestBody EnrollmentRequest request) {
    return enrollmentService.create(request);
  }

  @GetMapping("/{enrollmentId}")
  @PreAuthorize("isAuthenticated()")
  public EnrollmentResponse getById(@PathVariable String enrollmentId) {
    return enrollmentService.getById(enrollmentId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<EnrollmentResponse> getAll() {
    return enrollmentService.getAll();
  }

  @PutMapping("/{enrollmentId}")
  @PreAuthorize("isAuthenticated()")
  public EnrollmentResponse update(
      @PathVariable String enrollmentId, @Valid @RequestBody EnrollmentRequest request) {
    return enrollmentService.update(enrollmentId, request);
  }

  @DeleteMapping("/{enrollmentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String enrollmentId) {
    enrollmentService.delete(enrollmentId);
  }
}
