package com.skillforge.assessment.entity;

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
@Table(name = "sf_certification")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certification {
  @Id
  @Column(name = "CertificationID", nullable = false, length = 64)
  private String certificationId;

  @Column(name = "EmployeeID", nullable = false, length = 64)
  private String employeeId;

  @Column(name = "CourseID", nullable = false, length = 64)
  private String courseId;

  @Column(name = "IssueDate", nullable = false)
  private LocalDate issueDate;

  @Column(name = "ExpiryDate", nullable = false)
  private LocalDate expiryDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private CertificationStatus status;
}
