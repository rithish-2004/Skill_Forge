package com.skillforge.assessment.controller;

import com.skillforge.assessment.dto.ResultRequest;
import com.skillforge.assessment.dto.ResultResponse;
import com.skillforge.assessment.service.ResultService;
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
@RequestMapping("/api/v1/results")
@RequiredArgsConstructor
public class ResultController {
  private final ResultService resultService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public ResultResponse create(@Valid @RequestBody ResultRequest request) {
    return resultService.create(request);
  }

  @GetMapping("/{resultId}")
  @PreAuthorize("isAuthenticated()")
  public ResultResponse getById(@PathVariable String resultId) {
    return resultService.getById(resultId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<ResultResponse> getAll() {
    return resultService.getAll();
  }

  @PutMapping("/{resultId}")
  @PreAuthorize("isAuthenticated()")
  public ResultResponse update(@PathVariable String resultId, @Valid @RequestBody ResultRequest request) {
    return resultService.update(resultId, request);
  }

  @DeleteMapping("/{resultId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String resultId) {
    resultService.delete(resultId);
  }
}
