package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateWorkHistoryRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateWorkHistoryResponse;
import com.example.zerocode.employeeregistration.service.controller.response.WorkHistoryResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.WorkHistoryNotFoundException;
import com.example.zerocode.employeeregistration.service.model.WorkHistory;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.repository.WorkHistoryRepository;
import com.example.zerocode.employeeregistration.service.service.WorkHistoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class WorkHistoryServiceImpl implements WorkHistoryService {

  private final WorkHistoryRepository workHistoryRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateWorkHistoryResponse addWorkHistory(CreateWorkHistoryRequest request, Long employeeId)
      throws EmployeeNotFoundException {
    log.info("successfully added work history with employee id {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id: " + employeeId));

    var workHistory = new WorkHistory();
    modelMapper.map(request, workHistory);
    workHistory.setEmployee(employee);
    workHistoryRepository.save(workHistory);

    var response = new CreateWorkHistoryResponse();
    response.setId(workHistory.getId());

    return response;
  }

  @Override
  public List<WorkHistoryResponse> getWorkHistory(Long employeeId)
      throws EmployeeNotFoundException {
    log.info("successfully retrieved work history with employeeId : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id: " + employeeId));

    List<WorkHistory> workHistories = workHistoryRepository.findByEmployee(employee);

    return workHistories.stream()
        .map(workHistory -> WorkHistoryResponse.builder()
            .id(workHistory.getId())
            .workPlace(workHistory.getWorkPlace())
            .jobTitle(workHistory.getJobTitle())
            .project(workHistory.getProject())
            .startDate(workHistory.getStartDate())
            .endDate(workHistory.getEndDate())
            .build())
        .toList();
  }


  @Override
  public CreateWorkHistoryResponse updateWorkHistory(CreateWorkHistoryRequest request,
      Long employeeId, Long workHistoryId)
      throws EmployeeNotFoundException, WorkHistoryNotFoundException {
    log.info("successfully updated work history with employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id: " + employeeId));

    var workHistory = workHistoryRepository.findById(workHistoryId)
        .orElseThrow(() -> new WorkHistoryNotFoundException(
            "Not found work history with id: " + workHistoryId));

    modelMapper.map(request, workHistory);
    workHistory.setEmployee(employee);
    workHistoryRepository.save(workHistory);

    var response = new CreateWorkHistoryResponse();
    response.setId(workHistory.getId());

    return response;
  }

  @Override
  public CreateWorkHistoryResponse deleteWorkHistory(Long employeeId, Long workHistoryId)
      throws EmployeeNotFoundException, WorkHistoryNotFoundException {
    log.info("successfully deleted work history with employee id : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var workHistory = workHistoryRepository.findById(workHistoryId)
        .orElseThrow(() -> new WorkHistoryNotFoundException(
            "Not found work history with id:" + workHistoryId));

    workHistoryRepository.delete(workHistory);

    var response = new CreateWorkHistoryResponse();
    response.setId(workHistory.getId());

    return response;
  }
}
