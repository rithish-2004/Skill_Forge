package com.skillforge.competency.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_skill_gap")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillGap {
  @Id
  @Column(name = "SkillGapID", nullable = false, length = 64)
  private String skillGapId;

  @Column(name = "EmployeeID", nullable = false, length = 64)
  private String employeeId;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "CompetencyID", referencedColumnName = "CompetencyID", nullable = false)
  private Competency competency;

  @Enumerated(EnumType.STRING)
  @Column(name = "GapLevel", nullable = false, length = 32)
  private GapLevel gapLevel;

  @Column(name = "DateIdentified", nullable = false)
  private LocalDate dateIdentified;
}
