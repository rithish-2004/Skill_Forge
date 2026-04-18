package com.skillforge.identity.dto;

import com.skillforge.identity.entity.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
  private String token;
  private String userId;
  private UserRole role;
}
