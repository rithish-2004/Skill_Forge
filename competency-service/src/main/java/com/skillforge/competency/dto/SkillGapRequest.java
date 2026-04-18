package com.skillforge.competency.dto;

import com.skillforge.competency.entity.GapLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class SkillGapRequest {
  @NotBlank private String employeeId;

  @NotBlank private String competencyId;

  @NotNull private GapLevel gapLevel;

  @NotNull private LocalDate dateIdentified;
}
