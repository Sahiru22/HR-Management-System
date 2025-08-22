package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateAllowanceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.AllowanceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateAllowanceResponse;
import com.example.zerocode.employeeregistration.service.exception.AllowanceNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Allowance;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.repository.AllowanceRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.AllowanceService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AllowanceServiceImpl implements AllowanceService {

  private final AllowanceRepository allowanceRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateAllowanceResponse addAllowance(CreateAllowanceRequest request)
      throws EmployeeNotFoundException {
    log.info("successfully adding allowance by employee id: {}", request.getEmployeeId());

    var allowance = new Allowance();
    modelMapper.map(request, allowance);

    if (request.getEmployeeId() != null) {
      Employee employee = employeeRepository.findById(request.getEmployeeId())
          .orElseThrow(() -> new EmployeeNotFoundException(
              "Not found employee with id:" + request.getEmployeeId()));
      allowance.setEmployee(employee);
    }
    allowanceRepository.save(allowance);

    var response = new CreateAllowanceResponse();
    response.setId(allowance.getId());

    return response;
  }

  @Override
  public List<AllowanceResponse> getAllowance(Long employeeId) throws EmployeeNotFoundException {
    log.info("getting allowance with employee Id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    List<Allowance> allowances = allowanceRepository.findByEmployee(employee);

    return allowances.stream()
        .map(allowance -> AllowanceResponse.builder()
            .id(allowance.getId())
            .allowanceType(allowance.getAllowanceType())
            .allowanceFee(allowance.getAllowanceFee())
            .allowanceDate(allowance.getAllowanceDate())
            .build())
        .toList();
  }

  @Override
  public List<AllowanceResponse> getAllowanceById(Long allowanceId)
      throws AllowanceNotFoundException {
    log.info("Getting allowance with allowance Id: {}", allowanceId);

    var allowance = allowanceRepository.findById(allowanceId)
        .orElseThrow(
            () -> new AllowanceNotFoundException("Not found allowance with id:" + allowanceId));

    return List.of(AllowanceResponse.builder()
        .id(allowance.getId())
        .allowanceType(allowance.getAllowanceType())
        .allowanceFee(allowance.getAllowanceFee())
        .allowanceDate(allowance.getAllowanceDate())
        .employeeId(allowance.getEmployee().getId())
        .employeeFirstName(allowance.getEmployee().getFirstName())
        .employeeLastName(allowance.getEmployee().getLastName())
        .build());
  }

  @Override
  public CreateAllowanceResponse updateAllowance(CreateAllowanceRequest request,
      Long allowanceId) throws EmployeeNotFoundException, AllowanceNotFoundException {
    log.info("update allowance with employee id : {}", request.getEmployeeId());

    var allowance = allowanceRepository.findById(allowanceId)
        .orElseThrow(
            () -> new AllowanceNotFoundException("Not found allowance with id:" + allowanceId));

    modelMapper.map(request, allowance);

    if (request.getEmployeeId() != null) {
      Employee employee = employeeRepository.findById(request.getEmployeeId())
          .orElseThrow(() -> new EmployeeNotFoundException(
              "Not found employee with id:" + request.getEmployeeId()));
      allowance.setEmployee(employee);
    }
    allowanceRepository.save(allowance);

    var response = new CreateAllowanceResponse();
    response.setId(allowance.getId());

    return response;
  }

  @Override
  public CreateAllowanceResponse deleteAllowance(Long allowanceId)
      throws AllowanceNotFoundException {
    log.info("delete allowance by id : {}", allowanceId);

    var allowance = allowanceRepository.findById(allowanceId)
        .orElseThrow(
            () -> new AllowanceNotFoundException("Not found allowance with id:" + allowanceId));

    allowanceRepository.delete(allowance);

    var response = new CreateAllowanceResponse();
    response.setId(allowance.getId());

    return response;
  }
}
