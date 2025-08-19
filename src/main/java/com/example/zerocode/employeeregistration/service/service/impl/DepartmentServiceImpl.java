package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateDepartmentRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateDepartmentResponse;
import com.example.zerocode.employeeregistration.service.controller.response.DepartmentResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Department;
import com.example.zerocode.employeeregistration.service.repository.DepartmentRepository;
import com.example.zerocode.employeeregistration.service.service.DepartmentService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateDepartmentResponse add(CreateDepartmentRequest request) {

    var department = new Department();
    modelMapper.map(request, department);
    departmentRepository.save(department);

    var response = new CreateDepartmentResponse();
    response.setId(department.getId());

    log.info("department added successfully: {}", department.getId());

    return response;
  }

  @Override
  public List<DepartmentResponse> getAll() {
    System.out.println("Getting all department");
    log.info("Getting all department");

    List<Department> departments = departmentRepository.findAll();

    return departments.stream()
        .map(department -> DepartmentResponse.builder()
            .id(department.getId())
            .name(department.getName())
            .build())
        .toList();
  }

  @Override
  public DepartmentResponse getById(Long departmentId) throws DepartmentNotFoundException {
    log.info("getting departments by id: {}", departmentId);

    var department = departmentRepository.findById(departmentId)
        .orElseThrow(
            () -> new DepartmentNotFoundException("Not found department with id:" + departmentId));

    return DepartmentResponse.builder()
        .id(department.getId())
        .name(department.getName())
        .build();
  }


  @Override
  public CreateDepartmentResponse deleteById(Long departmentId) throws DepartmentNotFoundException {
    log.info("department delete by id : {}", departmentId);

    var department = departmentRepository.findById(departmentId)
        .orElseThrow(
            () -> new DepartmentNotFoundException("Not found department with id:" + departmentId));

    departmentRepository.delete(department);

    var response = new CreateDepartmentResponse();
    response.setId(department.getId());

    return response;
  }

  @Override
  public CreateDepartmentResponse updateById(Long departmentId, CreateDepartmentRequest request)
      throws DepartmentNotFoundException {
    log.info("update by id: {}", departmentId);

    var department = departmentRepository.findById(departmentId)
        .orElseThrow(
            () -> new DepartmentNotFoundException("Not found department with id:" + departmentId));

    modelMapper.map(request, department);
    departmentRepository.save(department);

    var response = new CreateDepartmentResponse();
    response.setId(department.getId());

    return response;

  }
}
