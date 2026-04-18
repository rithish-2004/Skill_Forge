package com.skillforge.assessment.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sf_assessment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {
  @Id
  @Column(name = "AssessmentID", nullable = false, length = 64)
  private String assessmentId;

  @Column(name = "CourseID", nullable = false, length = 64)
  private String courseId;

  @Enumerated(EnumType.STRING)
  @Column(name = "Type", nullable = false, length = 32)
  private AssessmentType type;

  @Column(name = "MaxScore", nullable = false)
  private Integer maxScore;

  @Column(name = "Date", nullable = false)
  private LocalDate date;

  @OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<Result> results = new ArrayList<>();
}
