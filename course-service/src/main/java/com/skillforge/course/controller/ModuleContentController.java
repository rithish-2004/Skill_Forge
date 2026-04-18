package com.skillforge.course.controller;

import com.skillforge.course.dto.ModuleContentRequest;
import com.skillforge.course.dto.ModuleContentResponse;
import com.skillforge.course.service.ModuleContentService;
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
@RequestMapping("/api/v1/module-contents")
@RequiredArgsConstructor
public class ModuleContentController {
  private final ModuleContentService moduleContentService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public ModuleContentResponse create(@Valid @RequestBody ModuleContentRequest request) {
    return moduleContentService.create(request);
  }

  @GetMapping("/{contentId}")
  @PreAuthorize("isAuthenticated()")
  public ModuleContentResponse getById(@PathVariable String contentId) {
    return moduleContentService.getById(contentId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<ModuleContentResponse> getAll() {
    return moduleContentService.getAll();
  }

  @PutMapping("/{contentId}")
  @PreAuthorize("isAuthenticated()")
  public ModuleContentResponse update(
      @PathVariable String contentId, @Valid @RequestBody ModuleContentRequest request) {
    return moduleContentService.update(contentId, request);
  }

  @DeleteMapping("/{contentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String contentId) {
    moduleContentService.delete(contentId);
  }
}
