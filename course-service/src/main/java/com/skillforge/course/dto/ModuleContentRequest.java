package com.skillforge.course.dto;

import com.skillforge.course.entity.ModuleContentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ModuleContentRequest {
  @NotBlank private String moduleId;

  @NotBlank private String name;

  @NotBlank private String url;

  @NotNull private ModuleContentStatus status;

  @NotNull @Positive private Integer duration;
}
