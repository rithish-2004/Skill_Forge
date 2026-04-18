package com.skillforge.compliance.serviceImpl;

import com.skillforge.compliance.dto.AuditRequest;
import com.skillforge.compliance.dto.AuditResponse;
import com.skillforge.compliance.entity.Audit;
import com.skillforge.compliance.exception.ApiException;
import com.skillforge.compliance.repository.AuditRepository;
import com.skillforge.compliance.service.AuditService;
import com.skillforge.compliance.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
  private final AuditRepository auditRepository;

  @Override
  @Transactional
  public AuditResponse create(AuditRequest request) {
    var entity =
        Audit.builder()
            .auditId(IdGenerator.nextAuditId())
            .hrid(request.getHrid())
            .scope(request.getScope())
            .findings(request.getFindings())
            .date(request.getDate())
            .status(request.getStatus())
            .build();
    return map(auditRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public AuditResponse getById(String auditId) {
    return map(find(auditId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<AuditResponse> getAll() {
    return auditRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public AuditResponse update(String auditId, AuditRequest request) {
    var entity = find(auditId);
    entity.setHrid(request.getHrid());
    entity.setScope(request.getScope());
    entity.setFindings(request.getFindings());
    entity.setDate(request.getDate());
    entity.setStatus(request.getStatus());
    return map(auditRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String auditId) {
    if (!auditRepository.existsById(auditId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Audit not found");
    }
    auditRepository.deleteById(auditId);
  }

  private Audit find(String auditId) {
    return auditRepository
        .findById(auditId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Audit not found"));
  }

  private AuditResponse map(Audit a) {
    return AuditResponse.builder()
        .auditId(a.getAuditId())
        .hrid(a.getHrid())
        .scope(a.getScope())
        .findings(a.getFindings())
        .date(a.getDate())
        .status(a.getStatus())
        .build();
  }
}
