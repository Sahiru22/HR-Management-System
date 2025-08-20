package com.example.zerocode.employeeregistration.service.controller.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSalaryRequest {

  @NotNull
  @Digits(fraction = 2, integer = 8)
  private BigDecimal basicSalary;

  @NotBlank
  @Size(max = 255)
  private String salarySchedule;

  @NotNull
  private String salaryDate;
}
