package com.skillforge.enrollment.dto;

import com.skillforge.enrollment.entity.AttendanceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class AttendanceRequest {
  @NotBlank private String enrollmentId;

  @NotNull private LocalDate date;

  @NotNull private AttendanceStatus status;
}
