package com.skillforge.assessment.dto;

import com.skillforge.assessment.entity.CertificationStatus;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CertificationResponse {
  private String certificationId;
  private String employeeId;
  private String courseId;
  private LocalDate issueDate;
  private LocalDate expiryDate;
  private CertificationStatus status;
}
