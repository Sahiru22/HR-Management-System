package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateLeaveRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateLeaveResponse;
import com.example.zerocode.employeeregistration.service.controller.response.LeaveResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.LeaveNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Leave;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.repository.LeaveRepository;
import com.example.zerocode.employeeregistration.service.service.LeaveService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LeaveServiceImpl implements LeaveService {

  private final LeaveRepository leaveRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateLeaveResponse addLeave(CreateLeaveRequest request, Long employeeId)
      throws EmployeeNotFoundException {
    log.info("successfully adding leaves with employee id:{}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var leave = new Leave();
    modelMapper.map(request, leave);
    leave.setEmployee(employee);
    leaveRepository.save(leave);

    var response = new CreateLeaveResponse();
    response.setId(leave.getId());

    return response;
  }

  @Override
  public List<LeaveResponse> getLeaves(Long employeeId) throws EmployeeNotFoundException {
    log.info("getting leaves with employeeId : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    List<Leave> leaves = leaveRepository.findByEmployee(employee);

    return leaves.stream()
        .map(leave -> LeaveResponse.builder()
            .id(leave.getId())
            .leaveType(leave.getLeaveType())
            .leaveBalance(leave.getLeaveBalance())
            .leaveDate(leave.getLeaveDate())
            .build())
        .toList();
  }

  @Override
  public CreateLeaveResponse updateLeave(CreateLeaveRequest request, Long employeeId, Long leaveId)
      throws EmployeeNotFoundException, LeaveNotFoundException {
    log.info("update leave by employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var leave = leaveRepository.findById(leaveId)
        .orElseThrow(() -> new LeaveNotFoundException("Not found leave with id:" + leaveId));

    modelMapper.map(request, leave);
    leave.setEmployee(employee);
    leaveRepository.save(leave);

    var response = new CreateLeaveResponse();
    response.setId(leave.getId());

    return response;
  }

  @Override
  public CreateLeaveResponse deleteLeave(Long employeeId, Long leaveId)
      throws EmployeeNotFoundException, LeaveNotFoundException {
    log.info("delete leave by employee id : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var leave = leaveRepository.findById(leaveId)
        .orElseThrow(() -> new LeaveNotFoundException("Not found leave with id:" + leaveId));

    leaveRepository.delete(leave);

    var response = new CreateLeaveResponse();
    response.setId(leave.getId());

    return response;
  }
}
