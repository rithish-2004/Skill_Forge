package com.skillforge.identity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuditLogRequest {
  @NotBlank private String userId;

  @NotBlank private String action;

  @NotBlank private String resource;
}
