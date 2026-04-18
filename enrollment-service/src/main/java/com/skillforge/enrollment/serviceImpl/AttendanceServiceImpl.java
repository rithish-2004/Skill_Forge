package com.skillforge.enrollment.serviceImpl;

import com.skillforge.enrollment.dto.AttendanceRequest;
import com.skillforge.enrollment.dto.AttendanceResponse;
import com.skillforge.enrollment.entity.Attendance;
import com.skillforge.enrollment.exception.ApiException;
import com.skillforge.enrollment.repository.AttendanceRepository;
import com.skillforge.enrollment.repository.EnrollmentRepository;
import com.skillforge.enrollment.service.AttendanceService;
import com.skillforge.enrollment.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
  private final AttendanceRepository attendanceRepository;
  private final EnrollmentRepository enrollmentRepository;

  @Override
  @Transactional
  public AttendanceResponse create(AttendanceRequest request) {
    var enrollment =
        enrollmentRepository
            .findById(request.getEnrollmentId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Enrollment not found"));
    var entity =
        Attendance.builder()
            .attendanceId(IdGenerator.nextAttendanceId())
            .enrollment(enrollment)
            .date(request.getDate())
            .status(request.getStatus())
            .build();
    enrollment.getAttendances().add(entity);
    return map(attendanceRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public AttendanceResponse getById(String attendanceId) {
    return map(find(attendanceId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<AttendanceResponse> getAll() {
    return attendanceRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public AttendanceResponse update(String attendanceId, AttendanceRequest request) {
    var entity = find(attendanceId);
    var enrollment =
        enrollmentRepository
            .findById(request.getEnrollmentId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Enrollment not found"));
    if (!entity.getEnrollment().getEnrollmentId().equals(enrollment.getEnrollmentId())) {
      entity.getEnrollment().getAttendances().remove(entity);
      entity.setEnrollment(enrollment);
      enrollment.getAttendances().add(entity);
    }
    entity.setDate(request.getDate());
    entity.setStatus(request.getStatus());
    return map(attendanceRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String attendanceId) {
    if (!attendanceRepository.existsById(attendanceId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Attendance not found");
    }
    attendanceRepository.deleteById(attendanceId);
  }

  private Attendance find(String attendanceId) {
    return attendanceRepository
        .findById(attendanceId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Attendance not found"));
  }

  private AttendanceResponse map(Attendance a) {
    return AttendanceResponse.builder()
        .attendanceId(a.getAttendanceId())
        .enrollmentId(a.getEnrollment().getEnrollmentId())
        .date(a.getDate())
        .status(a.getStatus())
        .build();
  }
}
