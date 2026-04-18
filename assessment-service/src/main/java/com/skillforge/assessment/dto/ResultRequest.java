package com.skillforge.assessment.dto;

import com.skillforge.assessment.entity.ResultStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ResultRequest {
  @NotBlank private String assessmentId;

  @NotBlank private String employeeId;

  @NotNull @PositiveOrZero private Integer score;

  @NotNull private ResultStatus status;
}
