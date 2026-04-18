package com.skillforge.course.serviceImpl;

import com.skillforge.course.dto.ModuleContentRequest;
import com.skillforge.course.dto.ModuleContentResponse;
import com.skillforge.course.entity.ModuleContent;
import com.skillforge.course.exception.ApiException;
import com.skillforge.course.repository.ModuleContentRepository;
import com.skillforge.course.repository.ModuleRepository;
import com.skillforge.course.service.ModuleContentService;
import com.skillforge.course.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModuleContentServiceImpl implements ModuleContentService {
  private final ModuleContentRepository moduleContentRepository;
  private final ModuleRepository moduleRepository;

  @Override
  @Transactional
  public ModuleContentResponse create(ModuleContentRequest request) {
    var module =
        moduleRepository
            .findById(request.getModuleId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Module not found"));
    var entity =
        ModuleContent.builder()
            .contentId(IdGenerator.nextModuleContentId())
            .module(module)
            .name(request.getName())
            .url(request.getUrl())
            .status(request.getStatus())
            .duration(request.getDuration())
            .build();
    module.getContents().add(entity);
    return map(moduleContentRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public ModuleContentResponse getById(String contentId) {
    return map(find(contentId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ModuleContentResponse> getAll() {
    return moduleContentRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public ModuleContentResponse update(String contentId, ModuleContentRequest request) {
    var entity = find(contentId);
    var module =
        moduleRepository
            .findById(request.getModuleId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Module not found"));
    if (!entity.getModule().getModuleId().equals(module.getModuleId())) {
      entity.getModule().getContents().remove(entity);
      entity.setModule(module);
      module.getContents().add(entity);
    }
    entity.setName(request.getName());
    entity.setUrl(request.getUrl());
    entity.setStatus(request.getStatus());
    entity.setDuration(request.getDuration());
    return map(moduleContentRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String contentId) {
    if (!moduleContentRepository.existsById(contentId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "ModuleContent not found");
    }
    moduleContentRepository.deleteById(contentId);
  }

  private ModuleContent find(String contentId) {
    return moduleContentRepository
        .findById(contentId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "ModuleContent not found"));
  }

  private ModuleContentResponse map(ModuleContent c) {
    return ModuleContentResponse.builder()
        .contentId(c.getContentId())
        .moduleId(c.getModule().getModuleId())
        .name(c.getName())
        .url(c.getUrl())
        .status(c.getStatus())
        .duration(c.getDuration())
        .build();
  }
}
