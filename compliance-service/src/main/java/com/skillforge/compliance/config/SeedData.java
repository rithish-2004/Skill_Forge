package com.skillforge.compliance.config;

import com.skillforge.compliance.entity.Audit;
import com.skillforge.compliance.entity.AuditStatus;
import com.skillforge.compliance.entity.ComplianceRecord;
import com.skillforge.compliance.entity.ComplianceRecordStatus;
import com.skillforge.compliance.repository.AuditRepository;
import com.skillforge.compliance.repository.ComplianceRecordRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SeedData {
  private final ComplianceRecordRepository complianceRecordRepository;
  private final AuditRepository auditRepository;

  @Bean
  public CommandLineRunner seedComplianceSample() {
    return args -> {
      if (!complianceRecordRepository.existsById("CMR0000000001")) {
        var record =
            ComplianceRecord.builder()
                .complianceId("CMR0000000001")
                .employeeId("EMP0000000001")
                .certificationId("CERT0000000001")
                .status(ComplianceRecordStatus.COMPLIANT)
                .date(LocalDate.now().minusDays(2))
                .build();
        complianceRecordRepository.save(record);
      }
      if (!auditRepository.existsById("AUD0000000001")) {
        var audit =
            Audit.builder()
                .auditId("AUD0000000001")
                .hrid("HR00000000001")
                .scope("Certification records Q1")
                .findings("No major issues detected")
                .date(LocalDate.now().minusDays(5))
                .status(AuditStatus.CLOSED)
                .build();
        auditRepository.save(audit);
      }
    };
  }
}
