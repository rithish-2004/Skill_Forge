package com.skillforge.assessment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.assessment.dto.CertificationRequest;
import com.skillforge.assessment.dto.CertificationResponse;
import com.skillforge.assessment.entity.Certification;
import com.skillforge.assessment.entity.CertificationStatus;
import com.skillforge.assessment.repository.CertificationRepository;
import com.skillforge.assessment.serviceImpl.CertificationServiceImpl;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CertificationServiceTest {
  @Mock private CertificationRepository certificationRepository;
  @InjectMocks private CertificationServiceImpl certificationService;

  @Test
  void create_persistsCertification() {
    var req = new CertificationRequest();
    req.setEmployeeId("EMP1");
    req.setCourseId("CRS1");
    req.setIssueDate(LocalDate.now());
    req.setExpiryDate(LocalDate.now().plusYears(1));
    req.setStatus(CertificationStatus.ACTIVE);
    when(certificationRepository.save(any(Certification.class)))
        .thenAnswer(
            inv -> {
              var c = (Certification) inv.getArgument(0);
              return Certification.builder()
                  .certificationId(c.getCertificationId())
                  .employeeId(c.getEmployeeId())
                  .courseId(c.getCourseId())
                  .issueDate(c.getIssueDate())
                  .expiryDate(c.getExpiryDate())
                  .status(c.getStatus())
                  .build();
            });
    CertificationResponse res = certificationService.create(req);
    assertThat(res.getEmployeeId()).isEqualTo("EMP1");
    verify(certificationRepository).save(any(Certification.class));
  }

  @Test
  void getById_returnsCertification() {
    var c =
        Certification.builder()
            .certificationId("CERT1")
            .employeeId("EMP1")
            .courseId("CRS1")
            .issueDate(LocalDate.now())
            .expiryDate(LocalDate.now().plusYears(1))
            .status(CertificationStatus.ACTIVE)
            .build();
    when(certificationRepository.findById("CERT1")).thenReturn(Optional.of(c));
    var res = certificationService.getById("CERT1");
    assertThat(res.getCertificationId()).isEqualTo("CERT1");
  }
}
