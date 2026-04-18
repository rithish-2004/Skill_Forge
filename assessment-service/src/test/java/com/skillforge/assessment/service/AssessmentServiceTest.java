package com.skillforge.assessment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.assessment.dto.AssessmentRequest;
import com.skillforge.assessment.dto.AssessmentResponse;
import com.skillforge.assessment.entity.Assessment;
import com.skillforge.assessment.entity.AssessmentType;
import com.skillforge.assessment.repository.AssessmentRepository;
import com.skillforge.assessment.serviceImpl.AssessmentServiceImpl;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {
  @Mock private AssessmentRepository assessmentRepository;
  @InjectMocks private AssessmentServiceImpl assessmentService;

  @Test
  void create_persistsAssessment() {
    var req = new AssessmentRequest();
    req.setCourseId("CRS1");
    req.setType(AssessmentType.QUIZ);
    req.setMaxScore(50);
    req.setDate(LocalDate.now());
    when(assessmentRepository.save(any(Assessment.class)))
        .thenAnswer(
            inv -> {
              var a = (Assessment) inv.getArgument(0);
              return Assessment.builder()
                  .assessmentId(a.getAssessmentId())
                  .courseId(a.getCourseId())
                  .type(a.getType())
                  .maxScore(a.getMaxScore())
                  .date(a.getDate())
                  .build();
            });
    AssessmentResponse res = assessmentService.create(req);
    assertThat(res.getCourseId()).isEqualTo("CRS1");
    verify(assessmentRepository).save(any(Assessment.class));
  }

  @Test
  void getById_returnsAssessment() {
    var a =
        Assessment.builder()
            .assessmentId("ASM1")
            .courseId("CRS1")
            .type(AssessmentType.EXAM)
            .maxScore(100)
            .date(LocalDate.now())
            .build();
    when(assessmentRepository.findById("ASM1")).thenReturn(Optional.of(a));
    var res = assessmentService.getById("ASM1");
    assertThat(res.getAssessmentId()).isEqualTo("ASM1");
  }
}
