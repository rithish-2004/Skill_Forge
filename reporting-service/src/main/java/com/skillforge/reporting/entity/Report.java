package com.skillforge.reporting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_report")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
  @Id
  @Column(name = "ReportID", nullable = false, length = 64)
  private String reportId;

  @Column(name = "Scope", nullable = false)
  private String scope;

  @Lob
  @Column(name = "Metrics", nullable = false)
  private String metrics;

  @Column(name = "GeneratedDate", nullable = false)
  private LocalDateTime generatedDate;
}
