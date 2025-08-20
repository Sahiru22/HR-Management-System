package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "salaries")
public class Salary extends BaseEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Digits(fraction = 2, integer = 8)
  private BigDecimal basicSalary;

  @NotBlank
  @Size(max = 255)
  private String salarySchedule;

  @NotNull
  private String salaryDate;

  @OneToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;
}
