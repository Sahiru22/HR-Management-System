package com.example.zerocode.employeeregistration.service.controller.response;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceResponse {

  private Long id;

  @NotBlank
  @Size(max = 255)
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
