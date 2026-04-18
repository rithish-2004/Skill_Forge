package com.skillforge.identity.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditLogResponse {
  private String auditId;
  private String userId;
  private String action;
  private String resource;
  private Instant timestamp;
}
