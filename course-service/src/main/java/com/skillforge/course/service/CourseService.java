package com.skillforge.course.service;

import com.skillforge.course.dto.CourseRequest;
import com.skillforge.course.dto.CourseResponse;
import java.util.List;

public interface CourseService {
  CourseResponse create(CourseRequest request);

  CourseResponse getById(String courseId);

  List<CourseResponse> getAll();

  CourseResponse update(String courseId, CourseRequest request);

  void delete(String courseId);
}
