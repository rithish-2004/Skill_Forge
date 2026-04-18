package com.skillforge.course.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.course.dto.CourseRequest;
import com.skillforge.course.dto.CourseResponse;
import com.skillforge.course.entity.Course;
import com.skillforge.course.entity.CourseStatus;
import com.skillforge.course.repository.CourseRepository;
import com.skillforge.course.serviceImpl.CourseServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
  @Mock private CourseRepository courseRepository;
  @InjectMocks private CourseServiceImpl courseService;

  @Test
  void create_persistsCourse() {
    var req = new CourseRequest();
    req.setTitle("T");
    req.setDescription("D");
    req.setTrainerId("TRN1");
    req.setDuration(10);
    req.setStatus(CourseStatus.ACTIVE);
    when(courseRepository.save(any(Course.class)))
        .thenAnswer(
            inv -> {
              var c = (Course) inv.getArgument(0);
              return Course.builder()
                  .courseId(c.getCourseId())
                  .title(c.getTitle())
                  .description(c.getDescription())
                  .trainerId(c.getTrainerId())
                  .duration(c.getDuration())
                  .status(c.getStatus())
                  .build();
            });
    CourseResponse res = courseService.create(req);
    assertThat(res.getTitle()).isEqualTo("T");
    verify(courseRepository).save(any(Course.class));
  }

  @Test
  void getById_returnsCourse() {
    var c =
        Course.builder()
            .courseId("CRS1")
            .title("T")
            .description("D")
            .trainerId("TRN1")
            .duration(1)
            .status(CourseStatus.ACTIVE)
            .build();
    when(courseRepository.findById("CRS1")).thenReturn(Optional.of(c));
    var res = courseService.getById("CRS1");
    assertThat(res.getCourseId()).isEqualTo("CRS1");
  }
}
