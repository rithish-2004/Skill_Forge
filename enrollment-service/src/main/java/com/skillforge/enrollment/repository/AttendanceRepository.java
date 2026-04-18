package com.skillforge.enrollment.repository;

import com.skillforge.enrollment.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {}
