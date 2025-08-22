package com.example.zerocode.employeeregistration.service.controller.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllowanceResponse {

  public Long id;
  private String allowanceType;
  private BigDecimal allowanceFee;
  private LocalDate allowanceDate;
  private Long employeeId;
  private String employeeFirstName;
  private String employeeLastName;
}
