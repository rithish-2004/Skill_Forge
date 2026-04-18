package com.skillforge.compliance.dto;

import com.skillforge.compliance.entity.ComplianceRecordStatus;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComplianceRecordResponse {
  private String complianceId;
  private String employeeId;
  private String certificationId;
  private ComplianceRecordStatus status;
  private LocalDate date;
}
