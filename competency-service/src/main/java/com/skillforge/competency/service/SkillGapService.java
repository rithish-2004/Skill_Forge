package com.skillforge.competency.service;

import com.skillforge.competency.dto.SkillGapRequest;
import com.skillforge.competency.dto.SkillGapResponse;
import java.util.List;

public interface SkillGapService {
  SkillGapResponse create(SkillGapRequest request);

  SkillGapResponse getById(String skillGapId);

  List<SkillGapResponse> getAll();

  SkillGapResponse update(String skillGapId, SkillGapRequest request);

  void delete(String skillGapId);
}
