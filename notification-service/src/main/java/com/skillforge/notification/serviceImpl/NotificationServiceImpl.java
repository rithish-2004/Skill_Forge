package com.skillforge.notification.serviceImpl;

import com.skillforge.notification.dto.NotificationRequest;
import com.skillforge.notification.dto.NotificationResponse;
import com.skillforge.notification.entity.Notification;
import com.skillforge.notification.exception.ApiException;
import com.skillforge.notification.repository.NotificationRepository;
import com.skillforge.notification.service.NotificationService;
import com.skillforge.notification.util.IdGenerator;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
  private final NotificationRepository notificationRepository;

  @Override
  @Transactional
  public NotificationResponse create(NotificationRequest request) {
    var created = request.getCreatedDate() != null ? request.getCreatedDate() : LocalDateTime.now();
    var entity =
        Notification.builder()
            .notificationId(IdGenerator.nextNotificationId())
            .userId(request.getUserId())
            .courseId(request.getCourseId())
            .message(request.getMessage())
            .category(request.getCategory())
            .status(request.getStatus())
            .createdDate(created)
            .build();
    return map(notificationRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public NotificationResponse getById(String notificationId) {
    return map(find(notificationId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<NotificationResponse> getAll() {
    return notificationRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public NotificationResponse update(String notificationId, NotificationRequest request) {
    var entity = find(notificationId);
    entity.setUserId(request.getUserId());
    entity.setCourseId(request.getCourseId());
    entity.setMessage(request.getMessage());
    entity.setCategory(request.getCategory());
    entity.setStatus(request.getStatus());
    if (request.getCreatedDate() != null) {
      entity.setCreatedDate(request.getCreatedDate());
    }
    return map(notificationRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String notificationId) {
    if (!notificationRepository.existsById(notificationId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Notification not found");
    }
    notificationRepository.deleteById(notificationId);
  }

  private Notification find(String notificationId) {
    return notificationRepository
        .findById(notificationId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Notification not found"));
  }

  private NotificationResponse map(Notification n) {
    return NotificationResponse.builder()
        .notificationId(n.getNotificationId())
        .userId(n.getUserId())
        .courseId(n.getCourseId())
        .message(n.getMessage())
        .category(n.getCategory())
        .status(n.getStatus())
        .createdDate(n.getCreatedDate())
        .build();
  }
}
