package com.skillforge.competency.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
@Table(name = "sf_competency")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competency {
  @Id
  @Column(name = "CompetencyID", nullable = false, length = 64)
  private String competencyId;

  @Column(name = "Name", nullable = false)
  private String name;

  @Column(name = "Description")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "Level", nullable = false, length = 32)
  private CompetencyLevel level;

  @OneToMany(mappedBy = "competency", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<SkillGap> skillGaps = new ArrayList<>();
}
