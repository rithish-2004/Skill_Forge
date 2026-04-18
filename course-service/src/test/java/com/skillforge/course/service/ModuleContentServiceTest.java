package com.skillforge.course.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.course.dto.ModuleContentRequest;
import com.skillforge.course.dto.ModuleContentResponse;
import com.skillforge.course.entity.Course;
import com.skillforge.course.entity.CourseStatus;
import com.skillforge.course.entity.Module;
import com.skillforge.course.entity.ModuleContent;
import com.skillforge.course.entity.ModuleContentStatus;
import com.skillforge.course.entity.ModuleStatus;
import com.skillforge.course.repository.ModuleContentRepository;
import com.skillforge.course.repository.ModuleRepository;
import com.skillforge.course.serviceImpl.ModuleContentServiceImpl;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ModuleContentServiceTest {
  @Mock private ModuleContentRepository moduleContentRepository;
  @Mock private ModuleRepository moduleRepository;
  @InjectMocks private ModuleContentServiceImpl moduleContentService;

  @Test
  void create_linksModule() {
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
    var module =
        Module.builder()
            .moduleId("MOD1")
            .course(course)
            .title("M")
            .duration(1)
            .status(ModuleStatus.ACTIVE)
            .contents(new ArrayList<>())
            .build();
    when(moduleRepository.findById("MOD1")).thenReturn(Optional.of(module));
    when(moduleContentRepository.save(any(ModuleContent.class)))
        .thenAnswer(
            inv -> {
              var c = (ModuleContent) inv.getArgument(0);
              return ModuleContent.builder()
                  .contentId(c.getContentId())
                  .module(c.getModule())
                  .name(c.getName())
                  .url(c.getUrl())
                  .status(c.getStatus())
                  .duration(c.getDuration())
                  .build();
            });
    var req = new ModuleContentRequest();
    req.setModuleId("MOD1");
    req.setName("N");
    req.setUrl("https://x");
    req.setStatus(ModuleContentStatus.ACTIVE);
    req.setDuration(10);
    ModuleContentResponse res = moduleContentService.create(req);
    assertThat(res.getModuleId()).isEqualTo("MOD1");
    verify(moduleContentRepository).save(any(ModuleContent.class));
  }
}
