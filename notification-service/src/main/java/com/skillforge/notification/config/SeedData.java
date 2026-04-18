package com.skillforge.notification.config;

import com.skillforge.notification.entity.Notification;
import com.skillforge.notification.entity.NotificationCategory;
import com.skillforge.notification.entity.NotificationStatus;
import com.skillforge.notification.repository.NotificationRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SeedData {
  private final NotificationRepository notificationRepository;

  @Bean
  public CommandLineRunner seedNotificationSample() {
    return args -> {
      if (notificationRepository.existsById("NOT0000000001")) {
        return;
      }
      var notification =
          Notification.builder()
              .notificationId("NOT0000000001")
              .userId("EMP0000000001")
              .courseId("CRS0000000001")
              .message("Your certification is expiring in 30 days")
              .category(NotificationCategory.REMINDER)
              .status(NotificationStatus.UNREAD)
              .createdDate(LocalDateTime.now().minusHours(3))
              .build();
      notificationRepository.save(notification);
    };
  }
}
