package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateSalaryRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateSalaryResponse;
import com.example.zerocode.employeeregistration.service.controller.response.SalaryResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.SalaryNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.Salary;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.repository.SalaryRepository;
import com.example.zerocode.employeeregistration.service.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CreateSalaryResponse addSalary(CreateSalaryRequest request, Long employeeId) throws EmployeeNotFoundException {
        System.out.println("successfully adding salaries with employee id:" + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id: " + employeeId));

            Salary salary = new Salary();

            salary.setId(request.getId());
            salary.setBasicSalary(request.getBasicSalary());
            salary.setSalarySchedule(request.getSalarySchedule());
            salary.setSalaryDate(request.getSalaryDate());

            salary.setEmployee(employee);

            salaryRepository.save(salary);

            CreateSalaryResponse response = new CreateSalaryResponse();
            response.setId(salary.getId());

            return response;
    }

    @Override
    public SalaryResponse getSalaryById(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("getting salary with employee id : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            Salary salary = salaryRepository.findByEmployee(employee);

            return SalaryResponse.builder()
                    .id(salary.getId())
                    .basicSalary(salary.getBasicSalary())
                    .salarySchedule(salary.getSalarySchedule())
                    .salaryDate(salary.getSalaryDate())
                    .employeeName(salary.getEmployee().getFirstName() + " " + salary.getEmployee().getLastName())
                    .build();
    }

    @Override
    public List<SalaryResponse> getAllSalaries(){
        System.out.println("successfully getting all salaries");

        List<Salary> salaries = salaryRepository.findAll();

        return salaries.stream()
                .map(salary -> SalaryResponse.builder()
                        .id(salary.getId())
                        .basicSalary(salary.getBasicSalary())
                        .salarySchedule(salary.getSalarySchedule())
                        .salaryDate(salary.getSalaryDate())
                        .employeeName(salary.getEmployee().getFirstName() + " " + salary.getEmployee().getLastName())
                        .build())
                .toList();
    }

    @Override
    public CreateSalaryResponse updateSalary(CreateSalaryRequest request, Long employeeId, Long salaryId) throws EmployeeNotFoundException, SalaryNotFoundException {
        System.out.println("update salary with employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            Salary salary = salaryRepository.findById(salaryId)
                            .orElseThrow(() -> new SalaryNotFoundException("Not found salary with id:" + salaryId));

                salary.setBasicSalary(request.getBasicSalary());
                salary.setSalarySchedule(request.getSalarySchedule());
                salary.setSalaryDate(request.getSalaryDate());

                salaryRepository.save(salary);

                CreateSalaryResponse response = new CreateSalaryResponse();
                response.setId(salary.getId());

                return response;
    }

    @Override
    public CreateSalaryResponse deleteSalary(Long employeeId, Long salaryId) throws SalaryNotFoundException {
        System.out.println("delete salary by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new SalaryNotFoundException("Not found employee with id: " + employeeId));

        Salary salary = salaryRepository.findById(salaryId)
                        .orElseThrow(() -> new SalaryNotFoundException("Not found salary with id: " + salaryId));

        salaryRepository.delete(salary);

        CreateSalaryResponse response = new CreateSalaryResponse();
        response.setId(salary.getId());

        return response;
    }
}
