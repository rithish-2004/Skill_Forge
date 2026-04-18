package com.skillforge.enrollment.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_enrollment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
  @Id
  @Column(name = "EnrollmentID", nullable = false, length = 64)
  private String enrollmentId;

  @Column(name = "CourseID", nullable = false, length = 64)
  private String courseId;

  @Column(name = "EmployeeID", nullable = false, length = 64)
  private String employeeId;

  @Column(name = "Date", nullable = false)
  private LocalDate date;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private EnrollmentStatus status;

  @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<Attendance> attendances = new ArrayList<>();
}
