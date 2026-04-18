package com.skillforge.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_module_content")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleContent {
  @Id
  @Column(name = "ContentID", nullable = false, length = 64)
  private String contentId;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID", nullable = false)
  private Module module;

  @Column(name = "Name", nullable = false)
  private String name;

  @Column(name = "URL", nullable = false, length = 2048)
  private String url;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private ModuleContentStatus status;

  @Column(name = "Duration", nullable = false)
  private Integer duration;
}
