package com.skillforge.competency.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skillforge.competency.dto.CompetencyRequest;
import com.skillforge.competency.dto.CompetencyResponse;
import com.skillforge.competency.entity.Competency;
import com.skillforge.competency.entity.CompetencyLevel;
import com.skillforge.competency.repository.CompetencyRepository;
import com.skillforge.competency.serviceImpl.CompetencyServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CompetencyServiceTest {
  @Mock private CompetencyRepository competencyRepository;
  @InjectMocks private CompetencyServiceImpl competencyService;

  @Test
  void create_persistsCompetency() {
    var req = new CompetencyRequest();
    req.setName("N");
    req.setDescription("D");
    req.setLevel(CompetencyLevel.EXPERT);
    when(competencyRepository.save(any(Competency.class)))
        .thenAnswer(
            inv -> {
              var c = (Competency) inv.getArgument(0);
              return Competency.builder()
                  .competencyId(c.getCompetencyId())
                  .name(c.getName())
                  .description(c.getDescription())
                  .level(c.getLevel())
                  .build();
            });
    CompetencyResponse res = competencyService.create(req);
    assertThat(res.getName()).isEqualTo("N");
    verify(competencyRepository).save(any(Competency.class));
  }

  @Test
  void getById_returnsCompetency() {
    var c =
        Competency.builder()
            .competencyId("COM1")
            .name("N")
            .description("D")
            .level(CompetencyLevel.BEGINNER)
            .build();
    when(competencyRepository.findById("COM1")).thenReturn(Optional.of(c));
    var res = competencyService.getById("COM1");
    assertThat(res.getCompetencyId()).isEqualTo("COM1");
  }
}
