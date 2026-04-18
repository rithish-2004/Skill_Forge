package com.skillforge.assessment.service;

import com.skillforge.assessment.dto.CertificationRequest;
import com.skillforge.assessment.dto.CertificationResponse;
import java.util.List;

public interface CertificationService {
  CertificationResponse create(CertificationRequest request);

  CertificationResponse getById(String certificationId);

  List<CertificationResponse> getAll();

  CertificationResponse update(String certificationId, CertificationRequest request);

  void delete(String certificationId);
}
