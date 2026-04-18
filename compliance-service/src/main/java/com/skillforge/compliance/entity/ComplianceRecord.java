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
@Table(name = "sf_compliance_record")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplianceRecord {
  @Id
  @Column(name = "ComplianceID", nullable = false, length = 64)
  private String complianceId;

  @Column(name = "EmployeeID", nullable = false, length = 64)
  private String employeeId;

  @Column(name = "CertificationID", nullable = false, length = 64)
  private String certificationId;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private ComplianceRecordStatus status;

  @Column(name = "Date", nullable = false)
  private LocalDate date;
}
