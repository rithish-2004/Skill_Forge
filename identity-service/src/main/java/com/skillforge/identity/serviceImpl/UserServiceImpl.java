package com.skillforge.identity.serviceImpl;

import com.skillforge.identity.dto.UserRequest;
import com.skillforge.identity.dto.UserResponse;
import com.skillforge.identity.entity.User;
import com.skillforge.identity.exception.ApiException;
import com.skillforge.identity.repository.UserRepository;
import com.skillforge.identity.service.UserService;
import com.skillforge.identity.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserResponse create(UserRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ApiException(HttpStatus.CONFLICT, "Email already exists");
    }
    var entity =
        User.builder()
            .userId(IdGenerator.nextUserId(request.getRole()))
            .name(request.getName())
            .role(request.getRole())
            .email(request.getEmail())
            .phone(request.getPhone())
            .status(request.getStatus())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();
    return map(userRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public UserResponse getById(String userId) {
    return map(find(userId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserResponse> getAll() {
    return userRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public UserResponse update(String userId, UserRequest request) {
    var entity = find(userId);
    if (!entity.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
      throw new ApiException(HttpStatus.CONFLICT, "Email already exists");
    }
    entity.setName(request.getName());
    entity.setRole(request.getRole());
    entity.setEmail(request.getEmail());
    entity.setPhone(request.getPhone());
    entity.setStatus(request.getStatus());
    if (request.getPassword() != null && !request.getPassword().isBlank()) {
      entity.setPassword(passwordEncoder.encode(request.getPassword()));
    }
    return map(userRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String userId) {
    if (!userRepository.existsById(userId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "User not found");
    }
    userRepository.deleteById(userId);
  }

  private User find(String userId) {
    return userRepository
        .findById(userId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
  }

  private UserResponse map(User u) {
    return UserResponse.builder()
        .userId(u.getUserId())
        .name(u.getName())
        .role(u.getRole())
        .email(u.getEmail())
        .phone(u.getPhone())
        .status(u.getStatus())
        .build();
  }
}
