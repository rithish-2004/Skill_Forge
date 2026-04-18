package com.skillforge.identity.dto;

import com.skillforge.identity.entity.UserRole;
import com.skillforge.identity.entity.UserStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
  private String userId;
  private String name;
  private UserRole role;
  private String email;
  private String phone;
  private UserStatus status;
}
