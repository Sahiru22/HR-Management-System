package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEducationalQualificationRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEducationalQualificationResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EducationalQualificationResponse;
import com.example.zerocode.employeeregistration.service.exception.EducationQualificationNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.EducationalQualificationService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EducationalQualificationController {

  private final EducationalQualificationService educationalQualificationService;

  @PostMapping(value = "/employees/{employee-id}/educational-qualifications", headers = "version=v1")
  public CreateEducationalQualificationResponse addNewEducationalQualification(
      @Valid @RequestBody CreateEducationalQualificationRequest request,
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return educationalQualificationService.addQualifications(request, employeeId);
  }

  @GetMapping(value = "/employees/{employee-id}/educational-qualifications", headers = "version=v1")
  public List<EducationalQualificationResponse> getEducationalQualifications(
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return educationalQualificationService.getQualificationByEmployeeId(employeeId);
  }

  @PutMapping(value = "/employees/{employee-id}/educational-qualifications/{educational-qualification-id}", headers = "version=v1")
  public CreateEducationalQualificationResponse updateEducationalQualifications(
      @Valid @RequestBody CreateEducationalQualificationRequest request,
      @PathVariable("employee-id") Long employeeId,
      @PathVariable("educational-qualification-id") Long educationalQualificationId)
      throws EmployeeNotFoundException, EducationQualificationNotFoundException {
    return educationalQualificationService.updateQualificationById(request, employeeId,
        educationalQualificationId);
  }

  @DeleteMapping(value = "/employees/{employee-id}/educational-qualifications/{educational-qualification-id}", headers = "version=v1")
  public CreateEducationalQualificationResponse deleteEducationalQualifications(
      @PathVariable("educational-qualification-id") Long educationalQualificationId,
      @PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException, EducationQualificationNotFoundException {
    return educationalQualificationService.deleteQualifications(educationalQualificationId,
        employeeId);
  }
}
