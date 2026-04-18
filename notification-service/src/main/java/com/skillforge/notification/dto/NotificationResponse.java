package com.skillforge.notification.dto;

import com.skillforge.notification.entity.NotificationCategory;
import com.skillforge.notification.entity.NotificationStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {
  private String notificationId;
  private String userId;
  private String courseId;
  private String message;
  private NotificationCategory category;
  private NotificationStatus status;
  private LocalDateTime createdDate;
}
