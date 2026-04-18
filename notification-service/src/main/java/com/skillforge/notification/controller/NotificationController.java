package com.skillforge.notification.controller;

import com.skillforge.notification.dto.NotificationRequest;
import com.skillforge.notification.dto.NotificationResponse;
import com.skillforge.notification.service.NotificationService;
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
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
  private final NotificationService notificationService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("isAuthenticated()")
  public NotificationResponse create(@Valid @RequestBody NotificationRequest request) {
    return notificationService.create(request);
  }

  @GetMapping("/{notificationId}")
  @PreAuthorize("isAuthenticated()")
  public NotificationResponse getById(@PathVariable String notificationId) {
    return notificationService.getById(notificationId);
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<NotificationResponse> getAll() {
    return notificationService.getAll();
  }

  @PutMapping("/{notificationId}")
  @PreAuthorize("isAuthenticated()")
  public NotificationResponse update(
      @PathVariable String notificationId, @Valid @RequestBody NotificationRequest request) {
    return notificationService.update(notificationId, request);
  }

  @DeleteMapping("/{notificationId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("isAuthenticated()")
  public void delete(@PathVariable String notificationId) {
    notificationService.delete(notificationId);
  }
}
