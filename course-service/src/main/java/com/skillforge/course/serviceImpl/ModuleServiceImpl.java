package com.skillforge.course.serviceImpl;

import com.skillforge.course.dto.ModuleRequest;
import com.skillforge.course.dto.ModuleResponse;
import com.skillforge.course.entity.Module;
import com.skillforge.course.exception.ApiException;
import com.skillforge.course.repository.CourseRepository;
import com.skillforge.course.repository.ModuleRepository;
import com.skillforge.course.service.ModuleService;
import com.skillforge.course.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {
  private final ModuleRepository moduleRepository;
  private final CourseRepository courseRepository;

  @Override
  @Transactional
  public ModuleResponse create(ModuleRequest request) {
    var course =
        courseRepository
            .findById(request.getCourseId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Course not found"));
    var entity =
        Module.builder()
            .moduleId(IdGenerator.nextModuleId())
            .course(course)
            .title(request.getTitle())
            .duration(request.getDuration())
            .status(request.getStatus())
            .build();
    course.getModules().add(entity);
    return map(moduleRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public ModuleResponse getById(String moduleId) {
    return map(find(moduleId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ModuleResponse> getAll() {
    return moduleRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public ModuleResponse update(String moduleId, ModuleRequest request) {
    var entity = find(moduleId);
    var course =
        courseRepository
            .findById(request.getCourseId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Course not found"));
    if (!entity.getCourse().getCourseId().equals(course.getCourseId())) {
      entity.getCourse().getModules().remove(entity);
      entity.setCourse(course);
      course.getModules().add(entity);
    }
    entity.setTitle(request.getTitle());
    entity.setDuration(request.getDuration());
    entity.setStatus(request.getStatus());
    return map(moduleRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String moduleId) {
    if (!moduleRepository.existsById(moduleId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Module not found");
    }
    moduleRepository.deleteById(moduleId);
  }

  private Module find(String moduleId) {
    return moduleRepository
        .findById(moduleId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Module not found"));
  }

  private ModuleResponse map(Module m) {
    return ModuleResponse.builder()
        .moduleId(m.getModuleId())
        .courseId(m.getCourse().getCourseId())
        .title(m.getTitle())
        .duration(m.getDuration())
        .status(m.getStatus())
        .build();
  }
}
