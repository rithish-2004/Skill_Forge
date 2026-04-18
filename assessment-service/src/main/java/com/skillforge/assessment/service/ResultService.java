package com.skillforge.assessment.service;

import com.skillforge.assessment.dto.ResultRequest;
import com.skillforge.assessment.dto.ResultResponse;
import java.util.List;

public interface ResultService {
  ResultResponse create(ResultRequest request);

  ResultResponse getById(String resultId);

  List<ResultResponse> getAll();

  ResultResponse update(String resultId, ResultRequest request);

  void delete(String resultId);
}
