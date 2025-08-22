package com.example.zerocode.employeeregistration.service.controller.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAllowanceRequest {

  @NotBlank
  @Size(max = 255)
  private String allowanceType;

  @NotNull
  @Digits(fraction = 2, integer = 8)
  private BigDecimal allowanceFee;

  @NotNull
  private LocalDate allowanceDate;

  @NotNull
  private Long employeeId;
}
