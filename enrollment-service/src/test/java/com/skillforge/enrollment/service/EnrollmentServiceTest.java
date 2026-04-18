package com.skillforge.enrollment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.enrollment.dto.EnrollmentRequest;
import com.skillforge.enrollment.dto.EnrollmentResponse;
import com.skillforge.enrollment.entity.Enrollment;
import com.skillforge.enrollment.entity.EnrollmentStatus;
import com.skillforge.enrollment.repository.EnrollmentRepository;
import com.skillforge.enrollment.serviceImpl.EnrollmentServiceImpl;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {
  @Mock private EnrollmentRepository enrollmentRepository;
  @InjectMocks private EnrollmentServiceImpl enrollmentService;

  @Test
  void create_persistsEnrollment() {
    var req = new EnrollmentRequest();
    req.setCourseId("CRS1");
    req.setEmployeeId("EMP1");
    req.setDate(LocalDate.of(2026, 1, 1));
    req.setStatus(EnrollmentStatus.ACTIVE);
    when(enrollmentRepository.save(any(Enrollment.class)))
        .thenAnswer(
            inv -> {
              var e = (Enrollment) inv.getArgument(0);
              return Enrollment.builder()
                  .enrollmentId(e.getEnrollmentId())
                  .courseId(e.getCourseId())
                  .employeeId(e.getEmployeeId())
                  .date(e.getDate())
                  .status(e.getStatus())
                  .build();
            });
    EnrollmentResponse res = enrollmentService.create(req);
    assertThat(res.getCourseId()).isEqualTo("CRS1");
    verify(enrollmentRepository).save(any(Enrollment.class));
  }

  @Test
  void getById_returnsEnrollment() {
    var e =
        Enrollment.builder()
            .enrollmentId("ENR1")
            .courseId("CRS1")
            .employeeId("EMP1")
            .date(LocalDate.now())
            .status(EnrollmentStatus.ACTIVE)
            .build();
    when(enrollmentRepository.findById("ENR1")).thenReturn(Optional.of(e));
    var res = enrollmentService.getById("ENR1");
    assertThat(res.getEnrollmentId()).isEqualTo("ENR1");
  }
}
