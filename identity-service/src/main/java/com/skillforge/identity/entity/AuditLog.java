package com.skillforge.identity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_audit_log")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
  @Id
  @Column(name = "AuditID", nullable = false, length = 64)
  private String auditId;

  @Column(name = "UserID", nullable = false, length = 64)
  private String userId;

  @Column(name = "Action", nullable = false)
  private String action;

  @Column(name = "Resource", nullable = false)
  private String resource;

  @Column(name = "Timestamp", nullable = false)
  private Instant timestamp;
}
