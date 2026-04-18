package com.skillforge.identity.serviceImpl;

import com.skillforge.identity.dto.AuditLogRequest;
import com.skillforge.identity.dto.AuditLogResponse;
import com.skillforge.identity.entity.AuditLog;
import com.skillforge.identity.exception.ApiException;
import com.skillforge.identity.repository.AuditLogRepository;
import com.skillforge.identity.service.AuditLogService;
import com.skillforge.identity.util.IdGenerator;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {
  private final AuditLogRepository auditLogRepository;

  @Override
  @Transactional
  public AuditLogResponse create(AuditLogRequest request) {
    var entity =
        AuditLog.builder()
            .auditId(IdGenerator.nextAuditLogId())
            .userId(request.getUserId())
            .action(request.getAction())
            .resource(request.getResource())
            .timestamp(Instant.now())
            .build();
    return map(auditLogRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public AuditLogResponse getById(String auditId) {
    return map(find(auditId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<AuditLogResponse> getAll() {
    return auditLogRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public AuditLogResponse update(String auditId, AuditLogRequest request) {
    var entity = find(auditId);
    entity.setUserId(request.getUserId());
    entity.setAction(request.getAction());
    entity.setResource(request.getResource());
    return map(auditLogRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String auditId) {
    if (!auditLogRepository.existsById(auditId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "AuditLog not found");
    }
    auditLogRepository.deleteById(auditId);
  }

  private AuditLog find(String auditId) {
    return auditLogRepository
        .findById(auditId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "AuditLog not found"));
  }

  private AuditLogResponse map(AuditLog a) {
    return AuditLogResponse.builder()
        .auditId(a.getAuditId())
        .userId(a.getUserId())
        .action(a.getAction())
        .resource(a.getResource())
        .timestamp(a.getTimestamp())
        .build();
  }
}
