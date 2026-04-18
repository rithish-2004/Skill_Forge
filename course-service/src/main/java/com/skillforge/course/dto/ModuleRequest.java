package com.skillforge.course.dto;

import com.skillforge.course.entity.ModuleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ModuleRequest {
  @NotBlank private String courseId;

  @NotBlank private String title;

  @NotNull @Positive private Integer duration;

  @NotNull private ModuleStatus status;
}
