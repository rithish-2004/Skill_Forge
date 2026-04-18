package com.skillforge.assessment.serviceImpl;

import com.skillforge.assessment.dto.AssessmentRequest;
import com.skillforge.assessment.dto.AssessmentResponse;
import com.skillforge.assessment.entity.Assessment;
import com.skillforge.assessment.exception.ApiException;
import com.skillforge.assessment.repository.AssessmentRepository;
import com.skillforge.assessment.service.AssessmentService;
import com.skillforge.assessment.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {
  private final AssessmentRepository assessmentRepository;

  @Override
  @Transactional
  public AssessmentResponse create(AssessmentRequest request) {
    var entity =
        Assessment.builder()
            .assessmentId(IdGenerator.nextAssessmentId())
            .courseId(request.getCourseId())
            .type(request.getType())
            .maxScore(request.getMaxScore())
            .date(request.getDate())
            .build();
    return map(assessmentRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public AssessmentResponse getById(String assessmentId) {
    return map(find(assessmentId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<AssessmentResponse> getAll() {
    return assessmentRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public AssessmentResponse update(String assessmentId, AssessmentRequest request) {
    var entity = find(assessmentId);
    entity.setCourseId(request.getCourseId());
    entity.setType(request.getType());
    entity.setMaxScore(request.getMaxScore());
    entity.setDate(request.getDate());
    return map(assessmentRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String assessmentId) {
    if (!assessmentRepository.existsById(assessmentId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Assessment not found");
    }
    assessmentRepository.deleteById(assessmentId);
  }

  private Assessment find(String assessmentId) {
    return assessmentRepository
        .findById(assessmentId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Assessment not found"));
  }

  private AssessmentResponse map(Assessment a) {
    return AssessmentResponse.builder()
        .assessmentId(a.getAssessmentId())
        .courseId(a.getCourseId())
        .type(a.getType())
        .maxScore(a.getMaxScore())
        .date(a.getDate())
        .build();
  }
}
