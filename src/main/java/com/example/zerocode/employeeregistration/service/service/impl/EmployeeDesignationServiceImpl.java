package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmployeeDesignationRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmployeeDesignationResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmployeeDesignationResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeDesignationNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Department;
import com.example.zerocode.employeeregistration.service.model.EmployeeDesignation;
import com.example.zerocode.employeeregistration.service.repository.DepartmentRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeDesignationRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.EmployeeDesignationService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeDesignationServiceImpl implements EmployeeDesignationService {

  private final EmployeeDesignationRepository employeeDesignationRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;
  private final DepartmentRepository departmentRepository;

  @Override
  public CreateEmployeeDesignationResponse addEmployeeDesignation(
      CreateEmployeeDesignationRequest request, Long employeeId)
      throws EmployeeNotFoundException, DepartmentNotFoundException {
    log.info("successfully adding employee designation with employee id:{}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var employeeDesignation = new EmployeeDesignation();
    modelMapper.map(request, employeeDesignation);
    if (request.getDepartmentId() != null) {
      Long departmentId = request.getDepartmentId();
      Department department = departmentRepository.findById(departmentId)
          .orElseThrow(() -> new DepartmentNotFoundException(
              "Department not found with id: " + departmentId));
      employeeDesignation.setEmployee(employee);
      employeeDesignation.setDepartment(department);
    }
    employeeDesignationRepository.save(employeeDesignation);

    var response = new CreateEmployeeDesignationResponse();
    response.setId(employeeDesignation.getId());

    return response;
  }

  @Override
  public List<EmployeeDesignationResponse> getEmployeeDesignation(Long employeeId)
      throws EmployeeNotFoundException {
    log.info("getting employee designation with employeeId : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    List<EmployeeDesignation> employeeDesignations = employeeDesignationRepository.findByEmployee(
        employee);

    return employeeDesignations.stream()
        .map(employeeDesignation -> EmployeeDesignationResponse.builder()
            .id(employeeDesignation.getId())
            .jobPosition(employeeDesignation.getJobPosition())
            .startDate(employeeDesignation.getStartDate())
            .endDate(employeeDesignation.getEndDate())
            .build())
        .toList();
  }

  @Override
  public CreateEmployeeDesignationResponse updateEmployeeDesignation(
      CreateEmployeeDesignationRequest request, Long employeeId, Long employeeDesignationId)
      throws EmployeeNotFoundException, EmployeeDesignationNotFoundException, DepartmentNotFoundException {
    log.info("updating employee designation with employee id:{}", employeeDesignationId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var employeeDesignation = employeeDesignationRepository.findById(
            employeeDesignationId)
        .orElseThrow(() -> new EmployeeDesignationNotFoundException(
            "Not found employee designation with id:" + employeeDesignationId));

    modelMapper.map(request, employeeDesignation);
    if (request.getDepartmentId() != null) {
      Long departmentId = request.getDepartmentId();
      Department department = departmentRepository.findById(departmentId)
          .orElseThrow(() -> new DepartmentNotFoundException(
              "Department not found with id: " + departmentId));
      employeeDesignation.setEmployee(employee);
      employeeDesignation.setDepartment(department);
    }
    employeeDesignationRepository.save(employeeDesignation);

    var response = new CreateEmployeeDesignationResponse();
    response.setId(employeeDesignation.getId());

    return response;
  }

  @Override
  public CreateEmployeeDesignationResponse deleteEmployeeDesignation(Long employeeDesignationId,
      Long employeeId) throws EmployeeNotFoundException, EmployeeDesignationNotFoundException {
    log.info("deleting employee designation with employee id:{}", employeeDesignationId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var employeeDesignation = employeeDesignationRepository.findById(
            employeeDesignationId)
        .orElseThrow(() -> new EmployeeDesignationNotFoundException(
            "Not found employee designation with id:" + employeeDesignationId));

    employeeDesignationRepository.delete(employeeDesignation);

    var response = new CreateEmployeeDesignationResponse();
    response.setId(employeeDesignation.getId());

    return response;
  }
}
