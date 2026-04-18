package com.skillforge.identity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @Column(name = "UserID", nullable = false, length = 64)
  private String userId;

  @Column(name = "Name", nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "Role", nullable = false, length = 32)
  private UserRole role;

  @Column(name = "Email", nullable = false, unique = true)
  private String email;

  @Column(name = "Phone", length = 32)
  private String phone;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private UserStatus status;

  @Column(name = "Password", nullable = false)
  private String password;
}
