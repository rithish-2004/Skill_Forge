package com.skillforge.assessment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_result")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {
  @Id
  @Column(name = "ResultID", nullable = false, length = 64)
  private String resultId;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "AssessmentID", referencedColumnName = "AssessmentID", nullable = false)
  private Assessment assessment;

  @Column(name = "EmployeeID", nullable = false, length = 64)
  private String employeeId;

  @Column(name = "Score", nullable = false)
  private Integer score;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private ResultStatus status;
}
