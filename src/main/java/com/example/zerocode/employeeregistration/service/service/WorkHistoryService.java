package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateWorkHistoryRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateWorkHistoryResponse;
import com.example.zerocode.employeeregistration.service.controller.response.WorkHistoryResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.WorkHistoryNotFoundException;
import java.util.List;

public interface WorkHistoryService {

  CreateWorkHistoryResponse addWorkHistory(CreateWorkHistoryRequest request, Long employeeId)
      throws EmployeeNotFoundException;

  List<WorkHistoryResponse> getWorkHistory(Long employeeId) throws EmployeeNotFoundException;

  CreateWorkHistoryResponse updateWorkHistory(CreateWorkHistoryRequest request, Long employeeId,
      Long workHistoryId) throws EmployeeNotFoundException, WorkHistoryNotFoundException;

  CreateWorkHistoryResponse deleteWorkHistory(Long employeeId, Long workHistoryId)
      throws EmployeeNotFoundException, WorkHistoryNotFoundException;
}
