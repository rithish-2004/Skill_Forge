package com.skillforge.competency.controller;

import com.skillforge.competency.dto.CompetencyRequest;
import com.skillforge.competency.dto.CompetencyResponse;
import com.skillforge.competency.service.CompetencyService;
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
@RequestMapping("/api/v1/competencies")
@RequiredArgsConstructor
public class CompetencyController {
  private final CompetencyService competencyService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public CompetencyResponse create(@Valid @RequestBody CompetencyRequest request) {
    return competencyService.create(request);
  }

  @GetMapping("/{competencyId}")
  @PreAuthorize("isAuthenticated()")
  public CompetencyResponse getById(@PathVariable String competencyId) {
    return competencyService.getById(competencyId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<CompetencyResponse> getAll() {
    return competencyService.getAll();
  }

  @PutMapping("/{competencyId}")
  @PreAuthorize("isAuthenticated()")
  public CompetencyResponse update(
      @PathVariable String competencyId, @Valid @RequestBody CompetencyRequest request) {
    return competencyService.update(competencyId, request);
  }

  @DeleteMapping("/{competencyId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String competencyId) {
    competencyService.delete(competencyId);
  }
}
