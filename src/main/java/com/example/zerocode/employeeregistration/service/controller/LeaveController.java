package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateLeaveRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateLeaveResponse;
import com.example.zerocode.employeeregistration.service.controller.response.LeaveResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.LeaveNotFoundException;
import com.example.zerocode.employeeregistration.service.service.LeaveService;
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
public class LeaveController {

  private final LeaveService leaveService;

  @PostMapping(value = "/employees/{employee-id}/leaves", headers = "version=v1")
  public CreateLeaveResponse add(@Valid @RequestBody CreateLeaveRequest request,
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return leaveService.addLeave(request, employeeId);
  }

  @GetMapping(value = "/employees/{employee-id}/leaves", headers = "version=v1")
  public List<LeaveResponse> get(@PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException {
    return leaveService.getLeaves(employeeId);
  }

  @PutMapping(value = "/employees/{employee-id}/leaves/{leave-id}", headers = "version=v1")
  public CreateLeaveResponse update(@Valid @RequestBody CreateLeaveRequest request,
      @PathVariable("employee-id") Long employeeId,
      @PathVariable("leave-id") Long leaveId)
      throws EmployeeNotFoundException, LeaveNotFoundException {
    return leaveService.updateLeave(request, employeeId, leaveId);
  }

  @DeleteMapping(value = "/employees/{employee-id}/leaves/{leave-id}", headers = "version=v1")
  public CreateLeaveResponse delete(@PathVariable("employee-id") Long employeeId,
      @PathVariable("leave-id") Long leaveId)
      throws EmployeeNotFoundException, LeaveNotFoundException {
    return leaveService.deleteLeave(employeeId, leaveId);
  }
}
