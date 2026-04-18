package com.skillforge.course.repository;

import com.skillforge.course.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, String> {}
