package com.skillforge.enrollment.dto;

import com.skillforge.enrollment.entity.AttendanceStatus;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceResponse {
  private String attendanceId;
  private String enrollmentId;
  private LocalDate date;
  private AttendanceStatus status;
}
