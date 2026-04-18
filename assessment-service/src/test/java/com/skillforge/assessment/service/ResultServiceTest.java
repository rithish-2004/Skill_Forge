package com.skillforge.assessment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.assessment.dto.ResultRequest;
import com.skillforge.assessment.dto.ResultResponse;
import com.skillforge.assessment.entity.Assessment;
import com.skillforge.assessment.entity.AssessmentType;
import com.skillforge.assessment.entity.Result;
import com.skillforge.assessment.entity.ResultStatus;
import com.skillforge.assessment.repository.AssessmentRepository;
import com.skillforge.assessment.repository.ResultRepository;
import com.skillforge.assessment.serviceImpl.ResultServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {
  @Mock private ResultRepository resultRepository;
  @Mock private AssessmentRepository assessmentRepository;
  @InjectMocks private ResultServiceImpl resultService;

  @Test
  void create_linksAssessment() {
    var assessment =
        Assessment.builder()
            .assessmentId("ASM1")
            .courseId("CRS1")
            .type(AssessmentType.EXAM)
            .maxScore(100)
            .date(LocalDate.now())
            .results(new ArrayList<>())
            .build();
    when(assessmentRepository.findById("ASM1")).thenReturn(Optional.of(assessment));
    when(resultRepository.save(any(Result.class)))
        .thenAnswer(
            inv -> {
              var r = (Result) inv.getArgument(0);
              return Result.builder()
                  .resultId(r.getResultId())
                  .assessment(r.getAssessment())
                  .employeeId(r.getEmployeeId())
                  .score(r.getScore())
                  .status(r.getStatus())
                  .build();
            });
    var req = new ResultRequest();
    req.setAssessmentId("ASM1");
    req.setEmployeeId("EMP1");
    req.setScore(80);
    req.setStatus(ResultStatus.PASSED);
    ResultResponse res = resultService.create(req);
    assertThat(res.getAssessmentId()).isEqualTo("ASM1");
    verify(resultRepository).save(any(Result.class));
  }
}
