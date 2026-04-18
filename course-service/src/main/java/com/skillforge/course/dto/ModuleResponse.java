package com.skillforge.course.dto;

import com.skillforge.course.entity.ModuleStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuleResponse {
  private String moduleId;
  private String courseId;
  private String title;
  private Integer duration;
  private ModuleStatus status;
}
