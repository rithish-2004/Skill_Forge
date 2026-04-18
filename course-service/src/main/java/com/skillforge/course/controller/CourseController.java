package com.skillforge.course.controller;

import com.skillforge.course.dto.CourseRequest;
import com.skillforge.course.dto.CourseResponse;
import com.skillforge.course.service.CourseService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
  private final CourseService courseService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public CourseResponse create(@Valid @RequestBody CourseRequest request) {
    return courseService.create(request);
  }

  @GetMapping("/{courseId}")
  @PreAuthorize("isAuthenticated()")
  public CourseResponse getById(@PathVariable String courseId) {
    return courseService.getById(courseId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<CourseResponse> getAll() {
    return courseService.getAll();
  }

  @PutMapping("/{courseId}")
  @PreAuthorize("isAuthenticated()")
  public CourseResponse update(@PathVariable String courseId, @Valid @RequestBody CourseRequest request) {
    return courseService.update(courseId, request);
  }

  @DeleteMapping("/{courseId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String courseId) {
    courseService.delete(courseId);
  }
}
