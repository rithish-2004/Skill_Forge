package com.skillforge.assessment.controller;

import com.skillforge.assessment.dto.AssessmentRequest;
import com.skillforge.assessment.dto.AssessmentResponse;
import com.skillforge.assessment.service.AssessmentService;
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
@RequestMapping("/api/v1/assessments")
@RequiredArgsConstructor
public class AssessmentController {
  private final AssessmentService assessmentService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public AssessmentResponse create(@Valid @RequestBody AssessmentRequest request) {
    return assessmentService.create(request);
  }

  @GetMapping("/{assessmentId}")
  @PreAuthorize("isAuthenticated()")
  public AssessmentResponse getById(@PathVariable String assessmentId) {
    return assessmentService.getById(assessmentId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<AssessmentResponse> getAll() {
    return assessmentService.getAll();
  }

  @PutMapping("/{assessmentId}")
  @PreAuthorize("isAuthenticated()")
  public AssessmentResponse update(
      @PathVariable String assessmentId, @Valid @RequestBody AssessmentRequest request) {
    return assessmentService.update(assessmentId, request);
  }

  @DeleteMapping("/{assessmentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String assessmentId) {
    assessmentService.delete(assessmentId);
  }
}
