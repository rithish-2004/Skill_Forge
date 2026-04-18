package com.skillforge.competency.dto;

import com.skillforge.competency.entity.GapLevel;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkillGapResponse {
  private String skillGapId;
  private String employeeId;
  private String competencyId;
  private GapLevel gapLevel;
  private LocalDate dateIdentified;
}
