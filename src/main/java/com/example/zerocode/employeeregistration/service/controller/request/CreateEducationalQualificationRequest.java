package com.example.zerocode.employeeregistration.service.controller.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEducationalQualificationRequest {

  @Size(max = 255)
  private String degree;

  @Size(max = 255)
  private String diploma;

  @Size(max = 255)
  private String institutionName;

  @Size(max = 255)
  private String fieldOfStudy;
}
