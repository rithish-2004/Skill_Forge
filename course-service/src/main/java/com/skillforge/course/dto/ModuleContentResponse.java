package com.skillforge.course.dto;

import com.skillforge.course.entity.ModuleContentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuleContentResponse {
  private String contentId;
  private String moduleId;
  private String name;
  private String url;
  private ModuleContentStatus status;
  private Integer duration;
}
