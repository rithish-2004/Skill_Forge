package com.skillforge.identity.serviceImpl;

import com.skillforge.identity.config.JwtService;
import com.skillforge.identity.dto.AuthResponse;
import com.skillforge.identity.dto.LoginRequest;
import com.skillforge.identity.dto.RegisterRequest;
import com.skillforge.identity.entity.User;
import com.skillforge.identity.entity.UserRole;
import com.skillforge.identity.entity.UserStatus;
import com.skillforge.identity.exception.ApiException;
import com.skillforge.identity.repository.UserRepository;
import com.skillforge.identity.service.AuthService;
import com.skillforge.identity.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  @Override
  @Transactional(readOnly = true)
  public AuthResponse login(LoginRequest request) {
    var user =
        userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }
    if (user.getStatus() != UserStatus.ACTIVE) {
      throw new ApiException(HttpStatus.FORBIDDEN, "Account inactive");
    }
    var token = jwtService.generateToken(user.getUserId(), user.getRole());
    return AuthResponse.builder().token(token).userId(user.getUserId()).role(user.getRole()).build();
  }

  @Override
  @Transactional
  public AuthResponse register(RegisterRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ApiException(HttpStatus.CONFLICT, "Email already exists");
    }
    var role = UserRole.EMPLOYEE;
    var entity =
        User.builder()
            .userId(IdGenerator.nextUserId(role))
            .name(request.getName())
            .role(role)
            .email(request.getEmail())
            .phone(request.getPhone())
            .status(UserStatus.ACTIVE)
            .password(passwordEncoder.encode(request.getPassword()))
            .build();
    userRepository.save(entity);
    var token = jwtService.generateToken(entity.getUserId(), entity.getRole());
    return AuthResponse.builder().token(token).userId(entity.getUserId()).role(entity.getRole()).build();
  }
}
