package com.skillforge.compliance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.compliance.dto.AuditRequest;
import com.skillforge.compliance.dto.AuditResponse;
import com.skillforge.compliance.entity.Audit;
import com.skillforge.compliance.entity.AuditStatus;
import com.skillforge.compliance.repository.AuditRepository;
import com.skillforge.compliance.serviceImpl.AuditServiceImpl;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuditServiceTest {
  @Mock private AuditRepository auditRepository;
  @InjectMocks private AuditServiceImpl auditService;

  @Test
  void create_persistsAudit() {
    var req = new AuditRequest();
    req.setHrid("HR1");
    req.setScope("S");
    req.setFindings("F");
    req.setDate(LocalDate.now());
    req.setStatus(AuditStatus.OPEN);
    when(auditRepository.save(any(Audit.class)))
        .thenAnswer(
            inv -> {
              var a = (Audit) inv.getArgument(0);
              return Audit.builder()
                  .auditId(a.getAuditId())
                  .hrid(a.getHrid())
                  .scope(a.getScope())
                  .findings(a.getFindings())
                  .date(a.getDate())
                  .status(a.getStatus())
                  .build();
            });
    AuditResponse res = auditService.create(req);
    assertThat(res.getHrid()).isEqualTo("HR1");
    verify(auditRepository).save(any(Audit.class));
  }

  @Test
  void getById_returnsAudit() {
    var a =
        Audit.builder()
            .auditId("AUD1")
            .hrid("HR1")
            .scope("S")
            .findings("F")
            .date(LocalDate.now())
            .status(AuditStatus.OPEN)
            .build();
    when(auditRepository.findById("AUD1")).thenReturn(Optional.of(a));
    var res = auditService.getById("AUD1");
    assertThat(res.getAuditId()).isEqualTo("AUD1");
  }
}
