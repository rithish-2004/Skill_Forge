package com.skillforge.assessment.serviceImpl;

import com.skillforge.assessment.dto.CertificationRequest;
import com.skillforge.assessment.dto.CertificationResponse;
import com.skillforge.assessment.entity.Certification;
import com.skillforge.assessment.exception.ApiException;
import com.skillforge.assessment.repository.CertificationRepository;
import com.skillforge.assessment.service.CertificationService;
import com.skillforge.assessment.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {
  private final CertificationRepository certificationRepository;

  @Override
  @Transactional
  public CertificationResponse create(CertificationRequest request) {
    var entity =
        Certification.builder()
            .certificationId(IdGenerator.nextCertificationId())
            .employeeId(request.getEmployeeId())
            .courseId(request.getCourseId())
            .issueDate(request.getIssueDate())
            .expiryDate(request.getExpiryDate())
            .status(request.getStatus())
            .build();
    return map(certificationRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public CertificationResponse getById(String certificationId) {
    return map(find(certificationId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<CertificationResponse> getAll() {
    return certificationRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public CertificationResponse update(String certificationId, CertificationRequest request) {
    var entity = find(certificationId);
    entity.setEmployeeId(request.getEmployeeId());
    entity.setCourseId(request.getCourseId());
    entity.setIssueDate(request.getIssueDate());
    entity.setExpiryDate(request.getExpiryDate());
    entity.setStatus(request.getStatus());
    return map(certificationRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String certificationId) {
    if (!certificationRepository.existsById(certificationId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Certification not found");
    }
    certificationRepository.deleteById(certificationId);
  }

  private Certification find(String certificationId) {
    return certificationRepository
        .findById(certificationId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Certification not found"));
  }

  private CertificationResponse map(Certification c) {
    return CertificationResponse.builder()
        .certificationId(c.getCertificationId())
        .employeeId(c.getEmployeeId())
        .courseId(c.getCourseId())
        .issueDate(c.getIssueDate())
        .expiryDate(c.getExpiryDate())
        .status(c.getStatus())
        .build();
  }
}
