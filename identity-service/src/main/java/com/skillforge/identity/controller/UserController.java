package com.skillforge.identity.controller;

import com.skillforge.identity.dto.UserRequest;
import com.skillforge.identity.dto.UserResponse;
import com.skillforge.identity.service.UserService;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAnyRole('ADMIN','HR')")
  public UserResponse create(@Valid @RequestBody UserRequest request) {
    return userService.create(request);
  }

  @GetMapping("/{userId}")
  @PreAuthorize("isAuthenticated()")
  public UserResponse getById(@PathVariable String userId) {
    return userService.getById(userId);
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN','HR')")
  public List<UserResponse> getAll() {
    return userService.getAll();
  }

  @PutMapping("/{userId}")
  @PreAuthorize("hasAnyRole('ADMIN','HR')")
  public UserResponse update(@PathVariable String userId, @Valid @RequestBody UserRequest request) {
    return userService.update(userId, request);
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('ADMIN')")
  public void delete(@PathVariable String userId) {
    userService.delete(userId);
  }
}
