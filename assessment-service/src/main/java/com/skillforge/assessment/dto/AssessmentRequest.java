package com.skillforge.assessment.dto;

import com.skillforge.assessment.entity.AssessmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.Data;

@Data
public class AssessmentRequest {
  @NotBlank private String courseId;

  @NotNull private AssessmentType type;

  @NotNull @Positive private Integer maxScore;

  @NotNull private LocalDate date;
}
