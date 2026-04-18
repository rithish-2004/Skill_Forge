package com.skillforge.identity.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.identity.dto.AuditLogRequest;
import com.skillforge.identity.entity.AuditLog;
import com.skillforge.identity.repository.AuditLogRepository;
import com.skillforge.identity.serviceImpl.AuditLogServiceImpl;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuditLogServiceTest {
  @Mock private AuditLogRepository auditLogRepository;
  @InjectMocks private AuditLogServiceImpl auditLogService;

  @Test
  void create_setsTimestamp() {
    var req = new AuditLogRequest();
    req.setUserId("ADM1");
    req.setAction("LOGIN");
    req.setResource("AUTH");
    when(auditLogRepository.save(any(AuditLog.class)))
        .thenAnswer(
            inv -> {
              var a = (AuditLog) inv.getArgument(0);
              return AuditLog.builder()
                  .auditId(a.getAuditId())
                  .userId(a.getUserId())
                  .action(a.getAction())
                  .resource(a.getResource())
                  .timestamp(a.getTimestamp())
                  .build();
            });
    var res = auditLogService.create(req);
    assertThat(res.getUserId()).isEqualTo("ADM1");
    assertThat(res.getTimestamp()).isNotNull();
    verify(auditLogRepository).save(any(AuditLog.class));
  }

  @Test
  void getById_returnsLog() {
    var a =
        AuditLog.builder()
            .auditId("ALG1")
            .userId("U")
            .action("A")
            .resource("R")
            .timestamp(Instant.now())
            .build();
    when(auditLogRepository.findById("ALG1")).thenReturn(Optional.of(a));
    var res = auditLogService.getById("ALG1");
    assertThat(res.getAuditId()).isEqualTo("ALG1");
  }
}
