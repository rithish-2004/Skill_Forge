package com.skillforge.assessment.dto;

import com.skillforge.assessment.entity.CertificationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class CertificationRequest {
  @NotBlank private String employeeId;

  @NotBlank private String courseId;

  @NotNull private LocalDate issueDate;

  @NotNull private LocalDate expiryDate;

  @NotNull private CertificationStatus status;
}
