package com.skillforge.enrollment.controller;

import com.skillforge.enrollment.dto.AttendanceRequest;
import com.skillforge.enrollment.dto.AttendanceResponse;
import com.skillforge.enrollment.service.AttendanceService;
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
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
public class AttendanceController {
  private final AttendanceService attendanceService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public AttendanceResponse create(@Valid @RequestBody AttendanceRequest request) {
    return attendanceService.create(request);
  }

  @GetMapping("/{attendanceId}")
  @PreAuthorize("isAuthenticated()")
  public AttendanceResponse getById(@PathVariable String attendanceId) {
    return attendanceService.getById(attendanceId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<AttendanceResponse> getAll() {
    return attendanceService.getAll();
  }

  @PutMapping("/{attendanceId}")
  @PreAuthorize("isAuthenticated()")
  public AttendanceResponse update(
      @PathVariable String attendanceId, @Valid @RequestBody AttendanceRequest request) {
    return attendanceService.update(attendanceId, request);
  }

  @DeleteMapping("/{attendanceId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String attendanceId) {
    attendanceService.delete(attendanceId);
  }
}
