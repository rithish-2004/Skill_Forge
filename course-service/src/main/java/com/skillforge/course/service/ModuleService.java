package com.skillforge.course.service;

import com.skillforge.course.dto.ModuleRequest;
import com.skillforge.course.dto.ModuleResponse;
import java.util.List;

public interface ModuleService {
  ModuleResponse create(ModuleRequest request);

  ModuleResponse getById(String moduleId);

  List<ModuleResponse> getAll();

  ModuleResponse update(String moduleId, ModuleRequest request);

  void delete(String moduleId);
}
