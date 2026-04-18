package com.skillforge.identity.service;

import com.skillforge.identity.dto.UserRequest;
import com.skillforge.identity.dto.UserResponse;
import java.util.List;

public interface UserService {
  UserResponse create(UserRequest request);

  UserResponse getById(String userId);

  List<UserResponse> getAll();

  UserResponse update(String userId, UserRequest request);

  void delete(String userId);
}
