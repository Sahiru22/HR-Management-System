package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateWorkHistoryRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateWorkHistoryResponse;
import com.example.zerocode.employeeregistration.service.controller.response.WorkHistoryResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.WorkHistoryNotFoundException;
import com.example.zerocode.employeeregistration.service.service.WorkHistoryService;
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
public class WorkHistoryController {

  private final WorkHistoryService workHistoryService;

  @PostMapping(value = "/employees/{employee-id}/work-histories", headers = "version=v1")
  public CreateWorkHistoryResponse addWorkHistory(
      @Valid @RequestBody CreateWorkHistoryRequest request,
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return workHistoryService.addWorkHistory(request, employeeId);
  }

  @GetMapping(value = "/employees/{employee-id}/work-histories", headers = "version=v1")
  public List<WorkHistoryResponse> getWorkHistory(@PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException {
    return workHistoryService.getWorkHistory(employeeId);
  }

  @PutMapping(value = "/employees/{employee-id}/work-histories/{work-history-id}", headers = "version=v1")
  public CreateWorkHistoryResponse updateWorkHistory(
      @Valid @RequestBody CreateWorkHistoryRequest request,
      @PathVariable("employee-id") Long employeeId,
      @PathVariable("work-history-id") Long workHistoryId)
      throws EmployeeNotFoundException, WorkHistoryNotFoundException {
    return workHistoryService.updateWorkHistory(request, employeeId, workHistoryId);
  }

  @DeleteMapping("/employees/{employee-id}/work-histories/{work-history-id}")
  public CreateWorkHistoryResponse deleteWorkHistory(@PathVariable("employee-id") Long employeeId,
      @PathVariable("work-history-id") Long workHistoryId)
      throws EmployeeNotFoundException, WorkHistoryNotFoundException {
    return workHistoryService.deleteWorkHistory(employeeId, workHistoryId);
  }
}
