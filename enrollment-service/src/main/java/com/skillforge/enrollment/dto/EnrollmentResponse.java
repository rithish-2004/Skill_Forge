package com.skillforge.enrollment.dto;

import com.skillforge.enrollment.entity.EnrollmentStatus;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentResponse {
  private String enrollmentId;
  private String courseId;
  private String employeeId;
  private LocalDate date;
  private EnrollmentStatus status;
}
