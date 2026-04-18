package com.skillforge.competency.config;

import com.skillforge.competency.entity.Competency;
import com.skillforge.competency.entity.CompetencyLevel;
import com.skillforge.competency.entity.GapLevel;
import com.skillforge.competency.entity.SkillGap;
import com.skillforge.competency.repository.CompetencyRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SeedData {
  private final CompetencyRepository competencyRepository;

  @Bean
  public CommandLineRunner seedCompetencyCatalog() {
    return args -> {
      if (competencyRepository.existsById("COM0000000001")) {
        return;
      }
      var competency =
          Competency.builder()
              .competencyId("COM0000000001")
              .name("Incident response")
              .description("Ability to coordinate security incident handling")
              .level(CompetencyLevel.ADVANCED)
              .build();
      var gap =
          SkillGap.builder()
              .skillGapId("SKG0000000001")
              .employeeId("EMP0000000001")
              .competency(competency)
              .gapLevel(GapLevel.MEDIUM)
              .dateIdentified(LocalDate.now().minusDays(30))
              .build();
      competency.getSkillGaps().add(gap);
      competencyRepository.save(competency);
    };
  }
}
