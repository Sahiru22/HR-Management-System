package com.example.zerocode.employeeregistration.service.controller.response;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationalQualificationResponse {

  private Long id;

  @Size(max = 255)
  private String degree;

  @Size(max = 255)
  private String diploma;

  @Size(max = 255)
  private String institutionName;

  @Size(max = 255)
  private String fieldOfStudy;
}
