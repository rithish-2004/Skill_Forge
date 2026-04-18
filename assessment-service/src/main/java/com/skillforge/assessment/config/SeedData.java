package com.skillforge.assessment.config;

import com.skillforge.assessment.entity.Assessment;
import com.skillforge.assessment.entity.AssessmentType;
import com.skillforge.assessment.entity.Certification;
import com.skillforge.assessment.entity.CertificationStatus;
import com.skillforge.assessment.entity.Result;
import com.skillforge.assessment.entity.ResultStatus;
import com.skillforge.assessment.repository.AssessmentRepository;
import com.skillforge.assessment.repository.CertificationRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SeedData {
  private final AssessmentRepository assessmentRepository;
  private final CertificationRepository certificationRepository;

  @Bean
  public CommandLineRunner seedAssessmentSample() {
    return args -> {
      if (assessmentRepository.existsById("ASM0000000001")) {
        return;
      }
      var assessment =
          Assessment.builder()
              .assessmentId("ASM0000000001")
              .courseId("CRS0000000001")
              .type(AssessmentType.EXAM)
              .maxScore(100)
              .date(LocalDate.now().minusDays(14))
              .build();
      var result =
          Result.builder()
              .resultId("RES0000000001")
              .assessment(assessment)
              .employeeId("EMP0000000001")
              .score(92)
              .status(ResultStatus.PASSED)
              .build();
      assessment.getResults().add(result);
      assessmentRepository.save(assessment);
      if (!certificationRepository.existsById("CERT0000000001")) {
        var cert =
            Certification.builder()
                .certificationId("CERT0000000001")
                .employeeId("EMP0000000001")
                .courseId("CRS0000000001")
                .issueDate(LocalDate.now().minusDays(10))
                .expiryDate(LocalDate.now().plusYears(1))
                .status(CertificationStatus.ACTIVE)
                .build();
        certificationRepository.save(cert);
      }
    };
  }
}
