package com.skillforge.competency.controller;

import com.skillforge.competency.dto.SkillGapRequest;
import com.skillforge.competency.dto.SkillGapResponse;
import com.skillforge.competency.service.SkillGapService;
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
@RequestMapping("/api/v1/skill-gaps")
@RequiredArgsConstructor
public class SkillGapController {
  private final SkillGapService skillGapService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public SkillGapResponse create(@Valid @RequestBody SkillGapRequest request) {
    return skillGapService.create(request);
  }

  @GetMapping("/{skillGapId}")
  @PreAuthorize("isAuthenticated()")
  public SkillGapResponse getById(@PathVariable String skillGapId) {
    return skillGapService.getById(skillGapId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<SkillGapResponse> getAll() {
    return skillGapService.getAll();
  }

  @PutMapping("/{skillGapId}")
  @PreAuthorize("isAuthenticated()")
  public SkillGapResponse update(
      @PathVariable String skillGapId, @Valid @RequestBody SkillGapRequest request) {
    return skillGapService.update(skillGapId, request);
  }

  @DeleteMapping("/{skillGapId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String skillGapId) {
    skillGapService.delete(skillGapId);
  }
}
