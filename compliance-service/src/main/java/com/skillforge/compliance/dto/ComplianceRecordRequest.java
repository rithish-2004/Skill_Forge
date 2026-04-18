package com.skillforge.compliance.dto;

import com.skillforge.compliance.entity.ComplianceRecordStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ComplianceRecordRequest {
  @NotBlank private String employeeId;

  @NotBlank private String certificationId;

  @NotNull private ComplianceRecordStatus status;

  @NotNull private LocalDate date;
}
