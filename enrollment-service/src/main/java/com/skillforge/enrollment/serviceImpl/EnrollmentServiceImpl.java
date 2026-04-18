package com.skillforge.enrollment.serviceImpl;

import com.skillforge.enrollment.dto.EnrollmentRequest;
import com.skillforge.enrollment.dto.EnrollmentResponse;
import com.skillforge.enrollment.entity.Enrollment;
import com.skillforge.enrollment.exception.ApiException;
import com.skillforge.enrollment.repository.EnrollmentRepository;
import com.skillforge.enrollment.service.EnrollmentService;
import com.skillforge.enrollment.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
  private final EnrollmentRepository enrollmentRepository;

  @Override
  @Transactional
  public EnrollmentResponse create(EnrollmentRequest request) {
    var entity =
        Enrollment.builder()
            .enrollmentId(IdGenerator.nextEnrollmentId())
            .courseId(request.getCourseId())
            .employeeId(request.getEmployeeId())
            .date(request.getDate())
            .status(request.getStatus())
            .build();
    return map(enrollmentRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public EnrollmentResponse getById(String enrollmentId) {
    return map(find(enrollmentId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<EnrollmentResponse> getAll() {
    return enrollmentRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public EnrollmentResponse update(String enrollmentId, EnrollmentRequest request) {
    var entity = find(enrollmentId);
    entity.setCourseId(request.getCourseId());
    entity.setEmployeeId(request.getEmployeeId());
    entity.setDate(request.getDate());
    entity.setStatus(request.getStatus());
    return map(enrollmentRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String enrollmentId) {
    if (!enrollmentRepository.existsById(enrollmentId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Enrollment not found");
    }
    enrollmentRepository.deleteById(enrollmentId);
  }

  private Enrollment find(String enrollmentId) {
    return enrollmentRepository
        .findById(enrollmentId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Enrollment not found"));
  }

  private EnrollmentResponse map(Enrollment e) {
    return EnrollmentResponse.builder()
        .enrollmentId(e.getEnrollmentId())
        .courseId(e.getCourseId())
        .employeeId(e.getEmployeeId())
        .date(e.getDate())
        .status(e.getStatus())
        .build();
  }
}
