package com.skillforge.enrollment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_attendance")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
  @Id
  @Column(name = "AttendanceID", nullable = false, length = 64)
  private String attendanceId;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "EnrollmentID", referencedColumnName = "EnrollmentID", nullable = false)
  private Enrollment enrollment;

  @Column(name = "Date", nullable = false)
  private LocalDate date;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private AttendanceStatus status;
}
