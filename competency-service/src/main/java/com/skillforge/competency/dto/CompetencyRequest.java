package com.skillforge.competency.dto;

import com.skillforge.competency.entity.CompetencyLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompetencyRequest {
  @NotBlank private String name;

  private String description;

  @NotNull private CompetencyLevel level;
}
