package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmployeeRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmployeeResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmployeeResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.EmployeeService;
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
public class EmployeeController {

  private final EmployeeService employeeService;

  @PostMapping(value = "/employees", headers = "version=v1")
  public CreateEmployeeResponse addNewEmployee(@RequestBody CreateEmployeeRequest request)
      throws DepartmentNotFoundException {
    return employeeService.add(request);
  }

  @GetMapping(value = "/employees", headers = "version=v1")
  public List<EmployeeResponse> getAllEmployees() {
    return employeeService.getAll();
  }

  @GetMapping(value = "/employees/{employee-id}", headers = "version=v1")
  public EmployeeResponse getEmployeeById(@PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException {
    return employeeService.getById(employeeId);
  }

  @DeleteMapping(value = "/employees/{employee-id}", headers = "version=v1")
  public CreateEmployeeResponse deleteEmployee(@PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException {
    return employeeService.deleteById(employeeId);
  }

  @PutMapping(value = "/employees/{employee-id}", headers = "version=v1")
  public CreateEmployeeResponse updateById(@PathVariable("employee-id") Long employeeId,
      @RequestBody CreateEmployeeRequest request)
      throws EmployeeNotFoundException, DepartmentNotFoundException {
    return employeeService.updateById(employeeId, request);
  }

  @GetMapping(value = "/departments/{department-id}/employees", headers = "version=v1")
  public List<EmployeeResponse> getAllDepartmentEmployees(
      @PathVariable("department-id") Long departmentId) throws DepartmentNotFoundException {
    return employeeService.getAllEmployeeByDepartmentId(departmentId);
  }
}
