package com.skillforge.assessment.service;

import com.skillforge.assessment.dto.AssessmentRequest;
import com.skillforge.assessment.dto.AssessmentResponse;
import java.util.List;

public interface AssessmentService {
  AssessmentResponse create(AssessmentRequest request);

  AssessmentResponse getById(String assessmentId);

  List<AssessmentResponse> getAll();

  AssessmentResponse update(String assessmentId, AssessmentRequest request);

  void delete(String assessmentId);
}
