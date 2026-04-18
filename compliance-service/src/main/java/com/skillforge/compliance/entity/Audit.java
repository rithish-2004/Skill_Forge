package com.skillforge.compliance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_audit")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Audit {
  @Id
  @Column(name = "AuditID", nullable = false, length = 64)
  private String auditId;

  @Column(name = "HRID", nullable = false, length = 64)
  private String hrid;

  @Column(name = "Scope", nullable = false)
  private String scope;

  @Column(name = "Findings", nullable = false, length = 4000)
  private String findings;

  @Column(name = "Date", nullable = false)
  private LocalDate date;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private AuditStatus status;
}
