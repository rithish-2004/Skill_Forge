package com.skillforge.notification.service;

import com.skillforge.notification.dto.NotificationRequest;
import com.skillforge.notification.dto.NotificationResponse;
import java.util.List;

public interface NotificationService {
  NotificationResponse create(NotificationRequest request);

  NotificationResponse getById(String notificationId);

  List<NotificationResponse> getAll();

  NotificationResponse update(String notificationId, NotificationRequest request);

  void delete(String notificationId);
}
