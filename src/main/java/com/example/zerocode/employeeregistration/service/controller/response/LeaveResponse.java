package com.example.zerocode.employeeregistration.service.controller.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveResponse {

  private Long id;

  @NotBlank
  @Size(max = 255)
  private String leaveType;

  @NotNull
  private Integer leaveBalance;

  @NotNull
  private String leaveDate;
}
