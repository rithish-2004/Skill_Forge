package com.skillforge.notification.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.notification.dto.NotificationRequest;
import com.skillforge.notification.dto.NotificationResponse;
import com.skillforge.notification.entity.Notification;
import com.skillforge.notification.entity.NotificationCategory;
import com.skillforge.notification.entity.NotificationStatus;
import com.skillforge.notification.repository.NotificationRepository;
import com.skillforge.notification.serviceImpl.NotificationServiceImpl;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {
  @Mock private NotificationRepository notificationRepository;
  @InjectMocks private NotificationServiceImpl notificationService;

  @Test
  void create_persistsNotification() {
    var req = new NotificationRequest();
    req.setUserId("EMP1");
    req.setCourseId("CRS1");
    req.setMessage("M");
    req.setCategory(NotificationCategory.INFO);
    req.setStatus(NotificationStatus.UNREAD);
    when(notificationRepository.save(any(Notification.class)))
        .thenAnswer(
            inv -> {
              var n = (Notification) inv.getArgument(0);
              return Notification.builder()
                  .notificationId(n.getNotificationId())
                  .userId(n.getUserId())
                  .courseId(n.getCourseId())
                  .message(n.getMessage())
                  .category(n.getCategory())
                  .status(n.getStatus())
                  .createdDate(n.getCreatedDate())
                  .build();
            });
    NotificationResponse res = notificationService.create(req);
    assertThat(res.getUserId()).isEqualTo("EMP1");
    verify(notificationRepository).save(any(Notification.class));
  }

  @Test
  void getById_returnsNotification() {
    var n =
        Notification.builder()
            .notificationId("NOT1")
            .userId("EMP1")
            .courseId("CRS1")
            .message("M")
            .category(NotificationCategory.ALERT)
            .status(NotificationStatus.READ)
            .createdDate(LocalDateTime.now())
            .build();
    when(notificationRepository.findById("NOT1")).thenReturn(Optional.of(n));
    var res = notificationService.getById("NOT1");
    assertThat(res.getNotificationId()).isEqualTo("NOT1");
  }
}
