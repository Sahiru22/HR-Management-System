package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "insurances")
public class Insurance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String insuranceType;

  @NotNull
  @Digits(fraction = 2, integer = 8)
  private BigDecimal insuranceFee;

  @NotNull
  private Integer period;

  @NotNull
  @Digits(fraction = 2, integer = 8)
  private BigDecimal monthlyDeductedAmount;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;
}
