package com.example.zerocode.employeeregistration.service.controller.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInsuranceRequest {

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
}
