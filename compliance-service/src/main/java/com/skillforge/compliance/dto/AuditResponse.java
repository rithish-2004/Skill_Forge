package com.skillforge.compliance.dto;

import com.skillforge.compliance.entity.AuditStatus;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditResponse {
  private String auditId;
  private String hrid;
  private String scope;
  private String findings;
  private LocalDate date;
  private AuditStatus status;
}
