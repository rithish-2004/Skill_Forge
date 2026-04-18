package com.skillforge.assessment.dto;

import com.skillforge.assessment.entity.AssessmentType;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssessmentResponse {
  private String assessmentId;
  private String courseId;
  private AssessmentType type;
  private Integer maxScore;
  private LocalDate date;
}
