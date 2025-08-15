package com.example.zerocode.employeeregistration.service.controller.response;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllowanceResponse {

  @NotBlank
  @Size(max = 255)
  private String allowanceType;

  @NotNull
  @Digits(fraction = 2, integer = 8)
  private BigDecimal allowanceFee;

  @NotNull
  private String allowanceDate;

}
