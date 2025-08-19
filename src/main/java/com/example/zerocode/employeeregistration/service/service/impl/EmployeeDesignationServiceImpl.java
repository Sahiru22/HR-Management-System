package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmployeeDesignationRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmployeeDesignationResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmployeeDesignationResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeDesignationNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Department;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.EmployeeDesignation;
import com.example.zerocode.employeeregistration.service.repository.DepartmentRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeDesignationRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.EmployeeDesignationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeDesignationServiceImpl implements EmployeeDesignationService {

    private final EmployeeDesignationRepository employeeDesignationRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public CreateEmployeeDesignationResponse addEmployeeDesignation(CreateEmployeeDesignationRequest request, Long employeeId, Long departmentId) throws EmployeeNotFoundException, DepartmentNotFoundException {
        System.out.println("successfully adding employee designation with employee id:" + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new DepartmentNotFoundException("Not found department with id:" + departmentId));

                EmployeeDesignation employeeDesignation = new EmployeeDesignation();

                employeeDesignation.setId(request.getId());
                employeeDesignation.setJobPosition(request.getJobPosition());
                employeeDesignation.setStartDate(request.getStartDate());
                employeeDesignation.setEndDate(request.getEndDate());

                employeeDesignation.setEmployee(employee);

                employeeDesignationRepository.save(employeeDesignation);

                CreateEmployeeDesignationResponse response = new CreateEmployeeDesignationResponse();
                response.setId(employeeDesignation.getId());

                return response;
    }

    @Override
    public List<EmployeeDesignationResponse> getEmployeeDesignation(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("getting employee designation with employeeId : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        List<EmployeeDesignation> employeeDesignations = employeeDesignationRepository.findByEmployee(employee);

        return employeeDesignations.stream()
                .map(employeeDesignation -> EmployeeDesignationResponse .builder()
                        .id(employeeDesignation.getId())
                        .jobPosition(employeeDesignation.getJobPosition())
                        .startDate(employeeDesignation.getStartDate())
                        .endDate(employeeDesignation.getEndDate())
                        .build())
                .toList();
    }

    @Override
    public CreateEmployeeDesignationResponse updateEmployeeDesignation(CreateEmployeeDesignationRequest request, Long employeeId, Long employeeDesignationId) throws EmployeeNotFoundException, EmployeeDesignationNotFoundException {
        System.out.println("update by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            EmployeeDesignation employeeDesignation = employeeDesignationRepository.findById(employeeDesignationId)
                            .orElseThrow(() -> new EmployeeDesignationNotFoundException("Not found employee designation with id:" + employeeDesignationId));

                employeeDesignation.setJobPosition(request.getJobPosition());
                employeeDesignation.setStartDate(request.getStartDate());
                employeeDesignation.setEndDate(request.getEndDate());

                employeeDesignationRepository.save(employeeDesignation);

                CreateEmployeeDesignationResponse response = new CreateEmployeeDesignationResponse();
                response.setId(employeeDesignation.getId());

                return response;
    }

    @Override
    public CreateEmployeeDesignationResponse deleteEmployeeDesignation(Long employeeDesignationId, Long employeeId) throws EmployeeNotFoundException, EmployeeDesignationNotFoundException {
        System.out.println("delete by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        EmployeeDesignation employeeDesignation = employeeDesignationRepository.findById(employeeDesignationId)
                        .orElseThrow(() -> new EmployeeDesignationNotFoundException("Not found employee designation with id:" + employeeDesignationId));

        employeeDesignationRepository.delete(employeeDesignation);

        CreateEmployeeDesignationResponse response = new CreateEmployeeDesignationResponse();
        response.setId(employeeDesignation.getId());

        return response;
    }
}
