package com.skillforge.identity.config;

import com.skillforge.identity.entity.User;
import com.skillforge.identity.entity.UserRole;
import com.skillforge.identity.entity.UserStatus;
import com.skillforge.identity.repository.UserRepository;
import com.skillforge.identity.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SeedData {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Bean
  public CommandLineRunner seedUsers() {
    return args -> {
      seedIfMissing("ADM0000000001", UserRole.ADMIN, "admin@skillforge.com", "System Admin");
      seedIfMissing("HR00000000001", UserRole.HR, "hr@skillforge.com", "HR Manager");
      seedIfMissing("TRN0000000001", UserRole.TRAINER, "trainer@skillforge.com", "Lead Trainer");
      seedIfMissing("EMP0000000001", UserRole.EMPLOYEE, "employee@skillforge.com", "Sample Employee");
    };
  }

  private void seedIfMissing(String userId, UserRole role, String email, String name) {
    if (userRepository.findByEmail(email).isPresent()) {
      return;
    }
    var user =
        User.builder()
            .userId(userId)
            .name(name)
            .role(role)
            .email(email)
            .phone("0000000000")
            .status(UserStatus.ACTIVE)
            .password(passwordEncoder.encode("ChangeMe!123"))
            .build();
    userRepository.save(user);
  }
}
