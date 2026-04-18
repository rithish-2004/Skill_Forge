package com.skillforge.reporting.config;

import com.skillforge.reporting.entity.Report;
import com.skillforge.reporting.repository.ReportRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SeedData {
  private final ReportRepository reportRepository;

  @Bean
  public CommandLineRunner seedReportSample() {
    return args -> {
      if (reportRepository.existsById("REP0000000001")) {
        return;
      }
      var report =
          Report.builder()
              .reportId("REP0000000001")
              .scope("Organization-wide training completion")
              .metrics("{\"completionRate\":0.87,\"activeEnrollments\":124}")
              .generatedDate(LocalDateTime.now().minusDays(1))
              .build();
      reportRepository.save(report);
    };
  }
}
