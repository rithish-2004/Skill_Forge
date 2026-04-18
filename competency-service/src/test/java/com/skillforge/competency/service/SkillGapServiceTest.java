package com.skillforge.competency.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.competency.dto.SkillGapRequest;
import com.skillforge.competency.dto.SkillGapResponse;
import com.skillforge.competency.entity.Competency;
import com.skillforge.competency.entity.CompetencyLevel;
import com.skillforge.competency.entity.GapLevel;
import com.skillforge.competency.entity.SkillGap;
import com.skillforge.competency.repository.CompetencyRepository;
import com.skillforge.competency.repository.SkillGapRepository;
import com.skillforge.competency.serviceImpl.SkillGapServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SkillGapServiceTest {
  @Mock private SkillGapRepository skillGapRepository;
  @Mock private CompetencyRepository competencyRepository;
  @InjectMocks private SkillGapServiceImpl skillGapService;

  @Test
  void create_linksCompetency() {
    var competency =
        Competency.builder()
            .competencyId("COM1")
            .name("N")
            .description("D")
            .level(CompetencyLevel.INTERMEDIATE)
            .skillGaps(new ArrayList<>())
            .build();
    when(competencyRepository.findById("COM1")).thenReturn(Optional.of(competency));
    when(skillGapRepository.save(any(SkillGap.class)))
        .thenAnswer(
            inv -> {
              var s = (SkillGap) inv.getArgument(0);
              return SkillGap.builder()
                  .skillGapId(s.getSkillGapId())
                  .employeeId(s.getEmployeeId())
                  .competency(s.getCompetency())
                  .gapLevel(s.getGapLevel())
                  .dateIdentified(s.getDateIdentified())
                  .build();
            });
    var req = new SkillGapRequest();
    req.setEmployeeId("EMP1");
    req.setCompetencyId("COM1");
    req.setGapLevel(GapLevel.HIGH);
    req.setDateIdentified(LocalDate.now());
    SkillGapResponse res = skillGapService.create(req);
    assertThat(res.getCompetencyId()).isEqualTo("COM1");
    verify(skillGapRepository).save(any(SkillGap.class));
  }
}
