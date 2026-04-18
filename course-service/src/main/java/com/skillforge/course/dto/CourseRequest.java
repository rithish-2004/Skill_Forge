package com.skillforge.course.dto;

import com.skillforge.course.entity.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CourseRequest {
  @NotBlank private String title;

  private String description;

  @NotBlank private String trainerId;

  @NotNull @Positive private Integer duration;

  @NotNull private CourseStatus status;
}
