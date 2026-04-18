package com.skillforge.competency.serviceImpl;

import com.skillforge.competency.dto.CompetencyRequest;
import com.skillforge.competency.dto.CompetencyResponse;
import com.skillforge.competency.entity.Competency;
import com.skillforge.competency.exception.ApiException;
import com.skillforge.competency.repository.CompetencyRepository;
import com.skillforge.competency.service.CompetencyService;
import com.skillforge.competency.util.IdGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompetencyServiceImpl implements CompetencyService {
  private final CompetencyRepository competencyRepository;

  @Override
  @Transactional
  public CompetencyResponse create(CompetencyRequest request) {
    var entity =
        Competency.builder()
            .competencyId(IdGenerator.nextCompetencyId())
            .name(request.getName())
            .description(request.getDescription())
            .level(request.getLevel())
            .build();
    return map(competencyRepository.save(entity));
  }

  @Override
  @Transactional(readOnly = true)
  public CompetencyResponse getById(String competencyId) {
    return map(find(competencyId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<CompetencyResponse> getAll() {
    return competencyRepository.findAll().stream().map(this::map).toList();
  }

  @Override
  @Transactional
  public CompetencyResponse update(String competencyId, CompetencyRequest request) {
    var entity = find(competencyId);
    entity.setName(request.getName());
    entity.setDescription(request.getDescription());
    entity.setLevel(request.getLevel());
    return map(competencyRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(String competencyId) {
    if (!competencyRepository.existsById(competencyId)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Competency not found");
    }
    competencyRepository.deleteById(competencyId);
  }

  private Competency find(String competencyId) {
    return competencyRepository
        .findById(competencyId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Competency not found"));
  }

  private CompetencyResponse map(Competency c) {
    return CompetencyResponse.builder()
        .competencyId(c.getCompetencyId())
        .name(c.getName())
        .description(c.getDescription())
        .level(c.getLevel())
        .build();
  }
}
