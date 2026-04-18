package com.skillforge.reporting.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportResponse {
  private String reportId;
  private String scope;
  private String metrics;
  private LocalDateTime generatedDate;
}
