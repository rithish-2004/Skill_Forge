package com.skillforge.compliance.serviceImpl;

import com.skillforge.compliance.dto.ComplianceRecordRequest;
import com.skillforge.compliance.dto.ComplianceRecordResponse;
import com.skillforge.compliance.entity.ComplianceRecord;
import com.skillforge.compliance.exception.ApiException;
import com.skillforge.compliance.repository.ComplianceRecordRepository;
import com.skillforge.compliance.service.ComplianceRecordService;
import com.skillforge.compliance.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComplianceRecordServiceImpl implements ComplianceRecordService {
  private final ComplianceRecordRepository complianceRecordRepository;

  @Override
  @Transactional
  public ComplianceRecordResponse create(ComplianceRecord request) {
    var entity =
        ComplianceRecord.builder()
            .complianceId(IdGenerator.nextComplianceRecordId())
            .employeeId(request.getEmployeeId())
            .certificationId(request.getCertificationId())
            .status(request.getStatus())
            .date(request.getDate())
            .build();
    return map(complianceRecordRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public ComplianceRecordResponse getById(String complianceId) {
    return map(find(complianceId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ComplianceRecordResponse> getAll() {
    return complianceRecordRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public ComplianceRecordResponse update(String complianceId, ComplianceRecordRequest request) {
    var entity = find(complianceId);
    entity.setEmployeeId(request.getEmployeeId());
    entity.setCertificationId(request.getCertificationId());
    entity.setStatus(request.getStatus());
    entity.setDate(request.getDate());
    return map(complianceRecordRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String complianceId) {
    if (!complianceRecordRepository.existsById(complianceId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "ComplianceRecord not found");
    }
    complianceRecordRepository.deleteById(complianceId);
  }

  private ComplianceRecord find(String complianceId) {
    return complianceRecordRepository
        .findById(complianceId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "ComplianceRecord not found"));
  }

  private ComplianceRecordResponse map(ComplianceRecord c) {
    return ComplianceRecordResponse.builder()
        .complianceId(c.getComplianceId())
        .employeeId(c.getEmployeeId())
        .certificationId(c.getCertificationId())
        .status(c.getStatus())
        .date(c.getDate())
        .build();
  }
}
