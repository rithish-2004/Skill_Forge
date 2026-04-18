package com.skillforge.enrollment.dto;

import com.skillforge.enrollment.entity.EnrollmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class EnrollmentRequest {
  @NotBlank private String courseId;

  @NotBlank private String employeeId;

  @NotNull private LocalDate date;

  @NotNull private EnrollmentStatus status;
}
