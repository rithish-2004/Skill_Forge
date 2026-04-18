package com.skillforge.competency.service;

import com.skillforge.competency.dto.CompetencyRequest;
import com.skillforge.competency.dto.CompetencyResponse;
import java.util.List;

public interface CompetencyService {
  CompetencyResponse create(CompetencyRequest request);

  CompetencyResponse getById(String competencyId);

  List<CompetencyResponse> getAll();

  CompetencyResponse update(String competencyId, CompetencyRequest request);

  void delete(String competencyId);
}
