package com.skillforge.enrollment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.enrollment.dto.AttendanceRequest;
import com.skillforge.enrollment.dto.AttendanceResponse;
import com.skillforge.enrollment.entity.Attendance;
import com.skillforge.enrollment.entity.AttendanceStatus;
import com.skillforge.enrollment.entity.Enrollment;
import com.skillforge.enrollment.entity.EnrollmentStatus;
import com.skillforge.enrollment.repository.AttendanceRepository;
import com.skillforge.enrollment.repository.EnrollmentRepository;
import com.skillforge.enrollment.serviceImpl.AttendanceServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest {
  @Mock private AttendanceRepository attendanceRepository;
  @Mock private EnrollmentRepository enrollmentRepository;
  @InjectMocks private AttendanceServiceImpl attendanceService;

  @Test
  void create_linksEnrollment() {
    var enrollment =
        Enrollment.builder()
            .enrollmentId("ENR1")
            .courseId("CRS1")
            .employeeId("EMP1")
            .date(LocalDate.now())
            .status(EnrollmentStatus.ACTIVE)
            .attendances(new ArrayList<>())
            .build();
    when(enrollmentRepository.findById("ENR1")).thenReturn(Optional.of(enrollment));
    when(attendanceRepository.save(any(Attendance.class)))
        .thenAnswer(
            inv -> {
              var a = (Attendance) inv.getArgument(0);
              return Attendance.builder()
                  .attendanceId(a.getAttendanceId())
                  .enrollment(a.getEnrollment())
                  .date(a.getDate())
                  .status(a.getStatus())
                  .build();
            });
    var req = new AttendanceRequest();
    req.setEnrollmentId("ENR1");
    req.setDate(LocalDate.now());
    req.setStatus(AttendanceStatus.PRESENT);
    AttendanceResponse res = attendanceService.create(req);
    assertThat(res.getEnrollmentId()).isEqualTo("ENR1");
    verify(attendanceRepository).save(any(Attendance.class));
  }
}
