package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateInsuranceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateInsuranceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.InsuranceResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.InsuranceNotFoundException;
import com.example.zerocode.employeeregistration.service.service.InsuranceService;
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
public class InsuranceController {

  private final InsuranceService insuranceService;

  @PostMapping(value = "/employees/{employee-id}/insurances", headers = "version=v1")
  public CreateInsuranceResponse add(@Valid @RequestBody CreateInsuranceRequest request,
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return insuranceService.addInsurance(request, employeeId);
  }

  @GetMapping(value = "/employees/{employee-id}/insurances", headers = "version=v1")
  public List<InsuranceResponse> get(@PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException {
    return insuranceService.getInsurance(employeeId);
  }

  @PutMapping(value = "/employees/{employee-id}/insurances/{insurance-id}", headers = "version=v1")
  public CreateInsuranceResponse update(@Valid @RequestBody CreateInsuranceRequest request,
      @PathVariable("employee-id") Long employeeId,
      @PathVariable("insurance-id") Long insuranceId)
      throws EmployeeNotFoundException, InsuranceNotFoundException {
    return insuranceService.updateInsurance(request, employeeId, insuranceId);
  }

  @DeleteMapping(value = "/employees/{employee-id}/insurances/{insurance-id}", headers = "version=v1")
  public CreateInsuranceResponse delete(@PathVariable("employee-id") Long employeeId,
      @PathVariable("insurance-id") Long insuranceId)
      throws InsuranceNotFoundException, EmployeeNotFoundException {
    return insuranceService.deleteInsurance(employeeId, insuranceId);
  }

}
