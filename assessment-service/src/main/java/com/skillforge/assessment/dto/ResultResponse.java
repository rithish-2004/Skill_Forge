package com.skillforge.assessment.dto;

import com.skillforge.assessment.entity.ResultStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultResponse {
  private String resultId;
  private String assessmentId;
  private String employeeId;
  private Integer score;
  private ResultStatus status;
}
