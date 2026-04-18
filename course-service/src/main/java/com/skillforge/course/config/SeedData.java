package com.skillforge.course.config;

import com.skillforge.course.entity.Course;
import com.skillforge.course.entity.CourseStatus;
import com.skillforge.course.entity.Module;
import com.skillforge.course.entity.ModuleContent;
import com.skillforge.course.entity.ModuleContentStatus;
import com.skillforge.course.entity.ModuleStatus;
import com.skillforge.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SeedData {
  private final CourseRepository courseRepository;

  @Bean
  public CommandLineRunner seedCourseCatalog() {
    return args -> {
      if (courseRepository.existsById("CRS0000000001")) {
        return;
      }
      var course =
          Course.builder()
              .courseId("CRS0000000001")
              .title("Enterprise Security Awareness")
              .description("Baseline security training for all employees")
              .trainerId("TRN0000000001")
              .duration(120)
              .status(CourseStatus.ACTIVE)
              .build();
      var module =
          Module.builder()
              .moduleId("MOD0000000001")
              .course(course)
              .title("Phishing fundamentals")
              .duration(45)
              .status(ModuleStatus.ACTIVE)
              .build();
      course.getModules().add(module);
      var content =
          ModuleContent.builder()
              .contentId("MCT0000000001")
              .module(module)
              .name("Video: Recognizing phishing")
              .url("https://cdn.skillforge.example/content/phishing-101.mp4")
              .status(ModuleContentStatus.ACTIVE)
              .duration(30)
              .build();
      module.getContents().add(content);
      courseRepository.save(course);
    };
  }
}
