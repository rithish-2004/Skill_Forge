package com.skillforge.course.service;

import com.skillforge.course.dto.ModuleContentRequest;
import com.skillforge.course.dto.ModuleContentResponse;
import java.util.List;

public interface ModuleContentService {
  ModuleContentResponse create(ModuleContentRequest request);

  ModuleContentResponse getById(String contentId);

  List<ModuleContentResponse> getAll();

  ModuleContentResponse update(String contentId, ModuleContentRequest request);

  void delete(String contentId);
}
