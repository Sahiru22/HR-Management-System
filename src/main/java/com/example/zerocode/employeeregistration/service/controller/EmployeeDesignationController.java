package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmployeeDesignationRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmployeeDesignationResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmployeeDesignationResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeDesignationNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.EmployeeDesignationService;
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
public class EmployeeDesignationController {

  private final EmployeeDesignationService employeeDesignationService;

  @PostMapping(value = "/employees/{employee-id}/employee-designations", headers = "version=v1")
  public CreateEmployeeDesignationResponse addEmployeeDesignation(
      @Valid @RequestBody CreateEmployeeDesignationRequest request,
      @PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException, DepartmentNotFoundException {
    return employeeDesignationService.addEmployeeDesignation(request, employeeId);
  }

  @GetMapping(value = "/employees/{employee-id}/employee-designations", headers = "version=v1")
  public List<EmployeeDesignationResponse> getEmployeeDesignation(
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return employeeDesignationService.getEmployeeDesignation(employeeId);
  }

  @PutMapping(value = "/employees/{employee-id}/employee-designations/{employee-designation-id}", headers = "version=v1")
  public CreateEmployeeDesignationResponse updateEmployeeDesignation(
      @Valid @RequestBody CreateEmployeeDesignationRequest request,
      @PathVariable("employee-id") Long employeeId,
      @PathVariable("employee-designation-id") Long employeeDesignationId)
      throws EmployeeNotFoundException, EmployeeDesignationNotFoundException, DepartmentNotFoundException {
    return employeeDesignationService.updateEmployeeDesignation(request, employeeId,
        employeeDesignationId);
  }

  @DeleteMapping(value = "/employees/{employee-id}/employee-designations/{employee-designation-id}", headers = "version=v1")
  public CreateEmployeeDesignationResponse deleteEmployeeDesignation(
      @PathVariable("employee-designation-id") Long employeeDesignationId,
      @PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException, EmployeeDesignationNotFoundException {
    return employeeDesignationService.deleteEmployeeDesignation(employeeDesignationId, employeeId);
  }
}
