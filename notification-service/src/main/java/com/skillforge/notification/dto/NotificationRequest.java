package com.skillforge.notification.dto;

import com.skillforge.notification.entity.NotificationCategory;
import com.skillforge.notification.entity.NotificationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class NotificationRequest {
  @NotBlank private String userId;

  private String courseId;

  @NotBlank private String message;

  @NotNull private NotificationCategory category;

  @NotNull private NotificationStatus status;

  private LocalDateTime createdDate;
}
