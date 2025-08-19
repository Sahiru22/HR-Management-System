package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateDependentDetailRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateDependentDetailResponse;
import com.example.zerocode.employeeregistration.service.controller.response.DependentDetailResponse;
import com.example.zerocode.employeeregistration.service.exception.DependentDetailsNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.DependentDetailService;
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
public class DependentDetailController {

  private final DependentDetailService dependentDetailService;

  @PostMapping(value = "/employees/{employee-id}/dependent-details", headers = "version=v1")
  public CreateDependentDetailResponse addDependentDetail(
      @Valid @RequestBody CreateDependentDetailRequest request,
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return dependentDetailService.addDependentDetails(request, employeeId);
  }

  @GetMapping(value = "/employees/{employee-id}/dependent-details", headers = "version=v1")
  public List<DependentDetailResponse> getDependentDetail(
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return dependentDetailService.getDependentDetails(employeeId);
  }

  @PutMapping(value = "/employees/{employee-id}/dependent-details/{dependent-details-id}", headers = "version=v1")
  public CreateDependentDetailResponse updateDependent(
      @Valid @RequestBody CreateDependentDetailRequest request,
      @PathVariable("employee-id") Long employeeId,
      @PathVariable("dependent-details-id") Long dependentDetailsId)
      throws EmployeeNotFoundException, DependentDetailsNotFoundException {
    return dependentDetailService.updateDependentDetails(request, employeeId, dependentDetailsId);
  }

  @DeleteMapping(value = "/employees/{employee-id}/dependent-details/{dependent-details-id}", headers = "version=v1")
  public CreateDependentDetailResponse deleteDependent(@PathVariable("employee-id") Long employeeId,
      @PathVariable("dependent-details-id") Long dependentDetailsId)
      throws EmployeeNotFoundException, DependentDetailsNotFoundException {
    return dependentDetailService.deleteDependent(employeeId, dependentDetailsId);
  }
}
