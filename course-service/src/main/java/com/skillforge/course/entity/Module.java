package com.skillforge.course.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_module")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Module {
  @Id
  @Column(name = "ModuleID", nullable = false, length = 64)
  private String moduleId;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "CourseID", referencedColumnName = "CourseID", nullable = false)
  private Course course;

  @Column(name = "Title", nullable = false)
  private String title;

  @Column(name = "Duration", nullable = false)
  private Integer duration;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private ModuleStatus status;

  @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<ModuleContent> contents = new ArrayList<>();
}
