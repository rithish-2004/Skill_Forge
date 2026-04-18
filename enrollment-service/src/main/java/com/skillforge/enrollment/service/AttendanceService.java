package com.skillforge.enrollment.service;

import com.skillforge.enrollment.dto.AttendanceRequest;
import com.skillforge.enrollment.dto.AttendanceResponse;
import java.util.List;

public interface AttendanceService {
  AttendanceResponse create(AttendanceRequest request);

  AttendanceResponse getById(String attendanceId);

  List<AttendanceResponse> getAll();

  AttendanceResponse update(String attendanceId, AttendanceRequest request);

  void delete(String attendanceId);
}
