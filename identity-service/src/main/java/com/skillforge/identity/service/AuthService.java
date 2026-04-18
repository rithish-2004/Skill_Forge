package com.skillforge.identity.service;

import com.skillforge.identity.dto.AuthResponse;
import com.skillforge.identity.dto.LoginRequest;
import com.skillforge.identity.dto.RegisterRequest;

public interface AuthService {
  AuthResponse login(LoginRequest request);

  AuthResponse register(RegisterRequest request);
}
