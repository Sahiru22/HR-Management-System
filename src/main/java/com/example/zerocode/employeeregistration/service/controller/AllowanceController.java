package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateAllowanceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.AllowanceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateAllowanceResponse;
import com.example.zerocode.employeeregistration.service.exception.AllowanceNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.AllowanceService;
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
public class AllowanceController {

  private final AllowanceService allowanceService;

  @PostMapping(value = "/employees/{employee-id}/allowances", headers = "version=v1")
  public CreateAllowanceResponse add(@Valid @RequestBody CreateAllowanceRequest request,
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return allowanceService.addAllowance(request, employeeId);
  }

  @GetMapping(value = "/employees/{employee-id}/allowances", headers = "version=v1")
  public List<AllowanceResponse> get(@PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException {
    return allowanceService.getAllowance(employeeId);
  }

  @GetMapping(value = "/employees/{employee-id}/allowances/{allowance-id}", headers = "version=v1")
  public List<AllowanceResponse> getById(@PathVariable("employee-id") Long employeeId,
      @PathVariable("allowance-id") Long allowanceId)
      throws EmployeeNotFoundException, AllowanceNotFoundException {
    return allowanceService.getAllowanceById(employeeId, allowanceId);
  }

  @PutMapping(value = "/employees/{employee-id}/allowances/{allowance-id}", headers = "version=v1")
  public CreateAllowanceResponse update(@Valid @RequestBody CreateAllowanceRequest request,
      @PathVariable("employee-id") Long employeeId,
      @PathVariable("allowance-id") Long allowanceId)
      throws EmployeeNotFoundException, AllowanceNotFoundException {
    return allowanceService.updateAllowance(request, employeeId, allowanceId);
  }

  @DeleteMapping(value = "/employees/{employee-id}/allowances/{allowance-id}", headers = "version=v1")
  public CreateAllowanceResponse delete(@PathVariable("employee-id") Long employeeId,
      @PathVariable("allowance-id") Long allowanceId)
      throws EmployeeNotFoundException, AllowanceNotFoundException {
    return allowanceService.deleteAllowance(employeeId, allowanceId);
  }
}
