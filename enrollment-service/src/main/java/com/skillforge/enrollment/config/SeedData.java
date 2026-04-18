package com.skillforge.enrollment.config;

import com.skillforge.enrollment.entity.Attendance;
import com.skillforge.enrollment.entity.AttendanceStatus;
import com.skillforge.enrollment.entity.Enrollment;
import com.skillforge.enrollment.entity.EnrollmentStatus;
import com.skillforge.enrollment.repository.EnrollmentRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SeedData {
  private final EnrollmentRepository enrollmentRepository;

  @Bean
  public CommandLineRunner seedEnrollmentSample() {
    return args -> {
      if (enrollmentRepository.existsById("ENR0000000001")) {
        return;
      }
      var enrollment =
          Enrollment.builder()
              .enrollmentId("ENR0000000001")
              .courseId("CRS0000000001")
              .employeeId("EMP0000000001")
              .date(LocalDate.now().minusDays(7))
              .status(EnrollmentStatus.ACTIVE)
              .build();
      var attendance =
          Attendance.builder()
              .attendanceId("ATT0000000001")
              .enrollment(enrollment)
              .date(LocalDate.now().minusDays(1))
              .status(AttendanceStatus.PRESENT)
              .build();
      enrollment.getAttendances().add(attendance);
      enrollmentRepository.save(enrollment);
    };
  }
}
