package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "educational_qualifications")
public class EducationalQualification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 255)
  private String degree;

  @Size(max = 255)
  private String diploma;

  @Size(max = 255)
  private String institutionName;

  @Size(max = 255)
  private String fieldOfStudy;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;
}
