package com.skillforge.course.dto;

import com.skillforge.course.entity.CourseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseResponse {
  private String courseId;
  private String title;
  private String description;
  private String trainerId;
  private Integer duration;
  private CourseStatus status;
}
