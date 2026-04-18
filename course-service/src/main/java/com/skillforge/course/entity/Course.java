package com.skillforge.course.entity;

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
@Table(name = "sf_course")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
  @Id
  @Column(name = "CourseID", nullable = false, length = 64)
  private String courseId;

  @Column(name = "Title", nullable = false)
  private String title;

  @Column(name = "Description")
  private String description;

  @Column(name = "TrainerID", nullable = false, length = 64)
  private String trainerId;

  @Column(name = "Duration", nullable = false)
  private Integer duration;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status", nullable = false, length = 32)
  private CourseStatus status;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<Module> modules = new ArrayList<>();
}
