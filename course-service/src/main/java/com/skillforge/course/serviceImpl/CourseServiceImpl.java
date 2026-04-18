package com.skillforge.course.serviceImpl;

import com.skillforge.course.dto.CourseRequest;
import com.skillforge.course.dto.CourseResponse;
import com.skillforge.course.entity.Course;
import com.skillforge.course.exception.ApiException;
import com.skillforge.course.repository.CourseRepository;
import com.skillforge.course.service.CourseService;
import com.skillforge.course.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
  private final CourseRepository courseRepository;

  @Override
  @Transactional
  public CourseResponse create(CourseRequest request) {
    var entity =
        Course.builder()
            .courseId(IdGenerator.nextCourseId())
            .title(request.getTitle())
            .description(request.getDescription())
            .trainerId(request.getTrainerId())
            .duration(request.getDuration())
            .status(request.getStatus())
            .build();
    return map(courseRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public CourseResponse getById(String courseId) {
    return map(find(courseId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<CourseResponse> getAll() {
    return courseRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public CourseResponse update(String courseId, CourseRequest request) {
    var entity = find(courseId);
    entity.setTitle(request.getTitle());
    entity.setDescription(request.getDescription());
    entity.setTrainerId(request.getTrainerId());
    entity.setDuration(request.getDuration());
    entity.setStatus(request.getStatus());
    return map(courseRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String courseId) {
    if (!courseRepository.existsById(courseId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Course not found");
    }
    courseRepository.deleteById(courseId);
  }

  private Course find(String courseId) {
    return courseRepository
        .findById(courseId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Course not found"));
  }

  private CourseResponse map(Course c) {
    return CourseResponse.builder()
        .courseId(c.getCourseId())
        .title(c.getTitle())
        .description(c.getDescription())
        .trainerId(c.getTrainerId())
        .duration(c.getDuration())
        .status(c.getStatus())
        .build();
  }
}
