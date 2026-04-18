package com.skillforge.course.controller;

import com.skillforge.course.dto.ModuleRequest;
import com.skillforge.course.dto.ModuleResponse;
import com.skillforge.course.service.ModuleService;
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
@RequestMapping("/api/v1/modules")
@RequiredArgsConstructor
public class ModuleController {
  private final ModuleService moduleService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public ModuleResponse create(@Valid @RequestBody ModuleRequest request) {
    return moduleService.create(request);
  }

  @GetMapping("/{moduleId}")
  @PreAuthorize("isAuthenticated()")
  public ModuleResponse getById(@PathVariable String moduleId) {
    return moduleService.getById(moduleId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<ModuleResponse> getAll() {
    return moduleService.getAll();
  }

  @PutMapping("/{moduleId}")
  @PreAuthorize("isAuthenticated()")
  public ModuleResponse update(@PathVariable String moduleId, @Valid @RequestBody ModuleRequest request) {
    return moduleService.update(moduleId, request);
  }

  @DeleteMapping("/{moduleId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String moduleId) {
    moduleService.delete(moduleId);
  }
}
