package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateSalaryRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateSalaryResponse;
import com.example.zerocode.employeeregistration.service.controller.response.SalaryResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.SalaryNotFoundException;
import java.util.List;

public interface SalaryService {

  CreateSalaryResponse addSalary(CreateSalaryRequest request, Long employeeId)
      throws EmployeeNotFoundException;

  SalaryResponse getSalaryById(Long employeeId) throws EmployeeNotFoundException;

  List<SalaryResponse> getAllSalaries();

  CreateSalaryResponse updateSalary(CreateSalaryRequest request, Long employeeId, Long salaryId)
      throws EmployeeNotFoundException, SalaryNotFoundException;

  CreateSalaryResponse deleteSalary(Long employeeId, Long salaryId) throws SalaryNotFoundException;
}
