package com.skillforge.competency.serviceImpl;

import com.skillforge.competency.dto.SkillGapRequest;
import com.skillforge.competency.dto.SkillGapResponse;
import com.skillforge.competency.entity.SkillGap;
import com.skillforge.competency.exception.ApiException;
import com.skillforge.competency.repository.CompetencyRepository;
import com.skillforge.competency.repository.SkillGapRepository;
import com.skillforge.competency.service.SkillGapService;
import com.skillforge.competency.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SkillGapServiceImpl implements SkillGapService {
  private final SkillGapRepository skillGapRepository;
  private final CompetencyRepository competencyRepository;

  @Override
  @Transactional
  public SkillGapResponse create(SkillGapRequest request) {
    var competency =
        competencyRepository
            .findById(request.getCompetencyId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Competency not found"));
    var entity =
        SkillGap.builder()
            .skillGapId(IdGenerator.nextSkillGapId())
            .employeeId(request.getEmployeeId())
            .competency(competency)
            .gapLevel(request.getGapLevel())
            .dateIdentified(request.getDateIdentified())
            .build();
    competency.getSkillGaps().add(entity);
    return map(skillGapRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public SkillGapResponse getById(String skillGapId) {
    return map(find(skillGapId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<SkillGapResponse> getAll() {
    return skillGapRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public SkillGapResponse update(String skillGapId, SkillGapRequest request) {
    var entity = find(skillGapId);
    var competency =
        competencyRepository
            .findById(request.getCompetencyId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Competency not found"));
    if (!entity.getCompetency().getCompetencyId().equals(competency.getCompetencyId())) {
      entity.getCompetency().getSkillGaps().remove(entity);
      entity.setCompetency(competency);
      competency.getSkillGaps().add(entity);
    }
    entity.setEmployeeId(request.getEmployeeId());
    entity.setGapLevel(request.getGapLevel());
    entity.setDateIdentified(request.getDateIdentified());
    return map(skillGapRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String skillGapId) {
    if (!skillGapRepository.existsById(skillGapId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "SkillGap not found");
    }
    skillGapRepository.deleteById(skillGapId);
  }

  private SkillGap find(String skillGapId) {
    return skillGapRepository
        .findById(skillGapId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "SkillGap not found"));
  }

  private SkillGapResponse map(SkillGap s) {
    return SkillGapResponse.builder()
        .skillGapId(s.getSkillGapId())
        .employeeId(s.getEmployeeId())
        .competencyId(s.getCompetency().getCompetencyId())
        .gapLevel(s.getGapLevel())
        .dateIdentified(s.getDateIdentified())
        .build();
  }
}
