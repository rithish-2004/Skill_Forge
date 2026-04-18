package com.skillforge.competency.dto;

import com.skillforge.competency.entity.CompetencyLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompetencyResponse {
  private String competencyId;
  private String name;
  private String description;
  private CompetencyLevel level;
}
