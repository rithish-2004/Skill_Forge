package com.skillforge.compliance.dto;

import com.skillforge.compliance.entity.AuditStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class AuditRequest {
  @NotBlank private String hrid;

  @NotBlank private String scope;

  @NotBlank private String findings;

  @NotNull private LocalDate date;

  @NotNull private AuditStatus status;
}
