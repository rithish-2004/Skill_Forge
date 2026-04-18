package com.skillforge.assessment.serviceImpl;

import com.skillforge.assessment.dto.ResultRequest;
import com.skillforge.assessment.dto.ResultResponse;
import com.skillforge.assessment.entity.Result;
import com.skillforge.assessment.exception.ApiException;
import com.skillforge.assessment.repository.AssessmentRepository;
import com.skillforge.assessment.repository.ResultRepository;
import com.skillforge.assessment.service.ResultService;
import com.skillforge.assessment.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
  private final ResultRepository resultRepository;
  private final AssessmentRepository assessmentRepository;

  @Override
  @Transactional
  public ResultResponse create(ResultRequest request) {
    var assessment =
        assessmentRepository
            .findById(request.getAssessmentId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Assessment not found"));
    var entity =
        Result.builder()
            .resultId(IdGenerator.nextResultId())
            .assessment(assessment)
            .employeeId(request.getEmployeeId())
            .score(request.getScore())
            .status(request.getStatus())
            .build();
    assessment.getResults().add(entity);
    return map(resultRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public ResultResponse getById(String resultId) {
    return map(find(resultId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ResultResponse> getAll() {
    return resultRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public ResultResponse update(String resultId, ResultRequest request) {
    var entity = find(resultId);
    var assessment =
        assessmentRepository
            .findById(request.getAssessmentId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Assessment not found"));
    if (!entity.getAssessment().getAssessmentId().equals(assessment.getAssessmentId())) {
      entity.getAssessment().getResults().remove(entity);
      entity.setAssessment(assessment);
      assessment.getResults().add(entity);
    }
    entity.setEmployeeId(request.getEmployeeId());
    entity.setScore(request.getScore());
    entity.setStatus(request.getStatus());
    return map(resultRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String resultId) {
    if (!resultRepository.existsById(resultId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Result not found");
    }
    resultRepository.deleteById(resultId);
  }

  private Result find(String resultId) {
    return resultRepository
        .findById(resultId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Result not found"));
  }

  private ResultResponse map(Result r) {
    return ResultResponse.builder()
        .resultId(r.getResultId())
        .assessmentId(r.getAssessment().getAssessmentId())
        .employeeId(r.getEmployeeId())
        .score(r.getScore())
        .status(r.getStatus())
        .build();
  }
}
