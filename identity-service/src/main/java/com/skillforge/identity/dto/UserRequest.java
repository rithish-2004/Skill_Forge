package com.skillforge.identity.dto;

import com.skillforge.identity.entity.UserRole;
import com.skillforge.identity.entity.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
  @NotBlank private String name;

  @NotNull private UserRole role;

  @NotBlank @Email private String email;

  private String phone;

  @NotNull private UserStatus status;

  @NotBlank private String password;
}
