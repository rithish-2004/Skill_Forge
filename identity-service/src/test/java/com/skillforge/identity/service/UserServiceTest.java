package com.skillforge.identity.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.identity.dto.UserRequest;
import com.skillforge.identity.dto.UserResponse;
import com.skillforge.identity.entity.User;
import com.skillforge.identity.entity.UserRole;
import com.skillforge.identity.entity.UserStatus;
import com.skillforge.identity.repository.UserRepository;
import com.skillforge.identity.serviceImpl.UserServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
  @Mock private UserRepository userRepository;
  @Mock private PasswordEncoder passwordEncoder;
  @InjectMocks private UserServiceImpl userService;

  @Test
  void create_persistsEncodedPassword() {
    var req = new UserRequest();
    req.setName("A");
    req.setRole(UserRole.EMPLOYEE);
    req.setEmail("a@b.com");
    req.setPhone("1");
    req.setStatus(UserStatus.ACTIVE);
    req.setPassword("raw");
    when(userRepository.existsByEmail("a@b.com")).thenReturn(false);
    when(passwordEncoder.encode("raw")).thenReturn("enc");
    when(userRepository.save(any(User.class)))
        .thenAnswer(
            inv -> {
              var u = (User) inv.getArgument(0);
              return User.builder()
                  .userId(u.getUserId())
                  .name(u.getName())
                  .role(u.getRole())
                  .email(u.getEmail())
                  .phone(u.getPhone())
                  .status(u.getStatus())
                  .password(u.getPassword())
                  .build();
            });
    UserResponse res = userService.create(req);
    assertThat(res.getEmail()).isEqualTo("a@b.com");
    verify(passwordEncoder).encode("raw");
    verify(userRepository).save(any(User.class));
  }

  @Test
  void getById_returnsUser() {
    var u =
        User.builder()
            .userId("EMP1")
            .name("N")
            .role(UserRole.EMPLOYEE)
            .email("e@e.com")
            .phone("p")
            .status(UserStatus.ACTIVE)
            .password("x")
            .build();
    when(userRepository.findById("EMP1")).thenReturn(Optional.of(u));
    var res = userService.getById("EMP1");
    assertThat(res.getUserId()).isEqualTo("EMP1");
  }
}
