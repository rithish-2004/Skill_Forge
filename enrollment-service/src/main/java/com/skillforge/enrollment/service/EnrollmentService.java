package com.skillforge.enrollment.service;

import com.skillforge.enrollment.dto.EnrollmentRequest;
import com.skillforge.enrollment.dto.EnrollmentResponse;
import java.util.List;

public interface EnrollmentService {
  EnrollmentResponse create(EnrollmentRequest request);

  EnrollmentResponse getById(String enrollmentId);

  List<EnrollmentResponse> getAll();

  EnrollmentResponse update(String enrollmentId, EnrollmentRequest request);

  void delete(String enrollmentId);
}
