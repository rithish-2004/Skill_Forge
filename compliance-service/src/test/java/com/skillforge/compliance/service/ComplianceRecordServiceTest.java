package com.skillforge.compliance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.compliance.dto.ComplianceRecordRequest;
import com.skillforge.compliance.dto.ComplianceRecordResponse;
import com.skillforge.compliance.entity.ComplianceRecord;
import com.skillforge.compliance.entity.ComplianceRecordStatus;
import com.skillforge.compliance.repository.ComplianceRecordRepository;
import com.skillforge.compliance.serviceImpl.ComplianceRecordServiceImpl;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ComplianceRecordServiceTest {
  @Mock private ComplianceRecordRepository complianceRecordRepository;
  @InjectMocks private ComplianceRecordServiceImpl complianceRecordService;

//  @Test
//  void create_persistsRecord() {
//    var req = new ComplianceRecordRequest();
//    req.setEmployeeId("EMP1");
//    req.setCertificationId("CERT1");
//    req.setStatus(ComplianceRecordStatus.PENDING);
//    req.setDate(LocalDate.now());
//    when(complianceRecordRepository.save(any(ComplianceRecord.class)))
//        .thenAnswer(
//            inv -> {
//              var c = (ComplianceRecord) inv.getArgument(0);
//              return ComplianceRecord.builder()
//                  .complianceId(c.getComplianceId())
//                  .employeeId(c.getEmployeeId())
//                  .certificationId(c.getCertificationId())
//                  .status(c.getStatus())
//                  .date(c.getDate())
//                  .build();
//            });
//    ComplianceRecordResponse res = complianceRecordService.create(req);
//    assertThat(res.getEmployeeId()).isEqualTo("EMP1");
//    verify(complianceRecordRepository).save(any(ComplianceRecord.class));
//  }

  @Test
  void getById_returnsRecord() {
    var c =
        ComplianceRecord.builder()
            .complianceId("CMR1")
            .employeeId("EMP1")
            .certificationId("CERT1")
            .status(ComplianceRecordStatus.COMPLIANT)
            .date(LocalDate.now())
            .build();
    when(complianceRecordRepository.findById("CMR1")).thenReturn(Optional.of(c));
    var res = complianceRecordService.getById("CMR1");
    assertThat(res.getComplianceId()).isEqualTo("CMR1");
  }
}
