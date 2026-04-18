package com.skillforge.notification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_notification")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
  @Id
  @Column(name = "NotificationID", nullable = false, length = 64)
  private String notificationId;

  @Column(name = "UserID", nullable = false, length = 64)
  private String userId;

  @Column(name = "CourseID", length = 64)
  private String courseId;

  @Column(name = "Message", nullable = false, length = 2000)
  private String message;

  @Enumerated(EnumType.STRING)
  @Column(name = "Category", nullable = false, length = 32)
  private NotificationCategory category;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private NotificationStatus status;

  @Column(name = "CreatedDate", nullable = false)
  private LocalDateTime createdDate;
}
