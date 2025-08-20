package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateLeaveRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateLeaveResponse;
import com.example.zerocode.employeeregistration.service.controller.response.LeaveResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.LeaveNotFoundException;
import java.util.List;

public interface LeaveService {

  CreateLeaveResponse addLeave(CreateLeaveRequest request, Long employeeId)
      throws EmployeeNotFoundException;

  List<LeaveResponse> getLeaves(Long employeeId) throws EmployeeNotFoundException;

  CreateLeaveResponse updateLeave(CreateLeaveRequest request, Long employeeId, Long leaveId)
      throws EmployeeNotFoundException, LeaveNotFoundException;

  CreateLeaveResponse deleteLeave(Long employeeId, Long leaveId)
      throws EmployeeNotFoundException, LeaveNotFoundException;
}
