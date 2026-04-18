package com.skillforge.reporting.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReportRequest {
  @NotBlank private String scope;

  @NotBlank private String metrics;

  private LocalDateTime generatedDate;
}
