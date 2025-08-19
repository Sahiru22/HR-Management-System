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
public class BonusResponse {

  private Long id;

  @NotBlank
  @Size(max = 255)
  private String bonusType;

  @NotNull
  @Digits(fraction = 2, integer = 8)
  private BigDecimal bonusAmount;

  @NotNull
  private String bonusDate;
}
