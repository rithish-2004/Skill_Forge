package com.skillforge.reporting.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.reporting.dto.ReportRequest;
import com.skillforge.reporting.dto.ReportResponse;
import com.skillforge.reporting.entity.Report;
import com.skillforge.reporting.repository.ReportRepository;
import com.skillforge.reporting.serviceImpl.ReportServiceImpl;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {
  @Mock private ReportRepository reportRepository;
  @InjectMocks private ReportServiceImpl reportService;

  @Test
  void create_persistsReport() {
    var req = new ReportRequest();
    req.setScope("S");
    req.setMetrics("M");
    when(reportRepository.save(any(Report.class)))
        .thenAnswer(
            inv -> {
              var r = (Report) inv.getArgument(0);
              return Report.builder()
                  .reportId(r.getReportId())
                  .scope(r.getScope())
                  .metrics(r.getMetrics())
                  .generatedDate(r.getGeneratedDate())
                  .build();
            });
    ReportResponse res = reportService.create(req);
    assertThat(res.getScope()).isEqualTo("S");
    verify(reportRepository).save(any(Report.class));
  }

  @Test
  void getById_returnsReport() {
    var r =
        Report.builder()
            .reportId("REP1")
            .scope("S")
            .metrics("M")
            .generatedDate(LocalDateTime.now())
            .build();
    when(reportRepository.findById("REP1")).thenReturn(Optional.of(r));
    var res = reportService.getById("REP1");
    assertThat(res.getReportId()).isEqualTo("REP1");
  }
}
