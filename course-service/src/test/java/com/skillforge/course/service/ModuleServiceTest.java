package com.skillforge.course.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.course.dto.ModuleRequest;
import com.skillforge.course.dto.ModuleResponse;
import com.skillforge.course.entity.Course;
import com.skillforge.course.entity.CourseStatus;
import com.skillforge.course.entity.Module;
import com.skillforge.course.entity.ModuleStatus;
import com.skillforge.course.repository.CourseRepository;
import com.skillforge.course.repository.ModuleRepository;
import com.skillforge.course.serviceImpl.ModuleServiceImpl;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ModuleServiceTest {
  @Mock private ModuleRepository moduleRepository;
  @Mock private CourseRepository courseRepository;
  @InjectMocks private ModuleServiceImpl moduleService;

  @Test
  void create_linksCourse() {
    var course =
        Course.builder()
            .courseId("CRS1")
            .title("T")
            .description("D")
            .trainerId("TRN1")
            .duration(1)
            .status(CourseStatus.ACTIVE)
            .modules(new ArrayList<>())
            .build();
    when(courseRepository.findById("CRS1")).thenReturn(Optional.of(course));
    when(moduleRepository.save(any(Module.class)))
        .thenAnswer(
            inv -> {
              var m = (Module) inv.getArgument(0);
              return Module.builder()
                  .moduleId(m.getModuleId())
                  .course(m.getCourse())
                  .title(m.getTitle())
                  .duration(m.getDuration())
                  .status(m.getStatus())
                  .build();
            });
    var req = new ModuleRequest();
    req.setCourseId("CRS1");
    req.setTitle("M");
    req.setDuration(5);
    req.setStatus(ModuleStatus.ACTIVE);
    ModuleResponse res = moduleService.create(req);
    assertThat(res.getCourseId()).isEqualTo("CRS1");
    verify(moduleRepository).save(any(Module.class));
  }
}
