package com.example.zerocode.employeeregistration.service.controller.response;

import com.example.zerocode.employeeregistration.service.model.Gender;
import com.example.zerocode.employeeregistration.service.model.MaritalStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

  private Long id;

  @NotBlank
  @Size(max = 60)
  private String firstName;

  @NotBlank
  @Size(max = 60)
  private String lastName;

  @NotNull
  private Integer age;

  @NotBlank
  @Size(max = 255)
  private String address;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @NotBlank
  @Email
  @Size(max = 60)
  private String email;

  private String bloodGroup;

  @NotNull
  @Enumerated(EnumType.STRING)
  private MaritalStatus maritalStatus;

  @NotNull
  private LocalDate birthDate;

  @NotBlank
  @Digits(integer = 10, fraction = 0)
  private String phoneNumber;

  @NotNull
  private Long departmentId;
}
