package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateAllowanceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.AllowanceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateAllowanceResponse;
import com.example.zerocode.employeeregistration.service.exception.AllowanceNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.AllowanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AllowanceController {

    private final AllowanceService allowanceService;

    @PostMapping(value = "/employees/{employee-id}/allowances",headers = "version=v1")
    public CreateAllowanceResponse add(@RequestBody CreateAllowanceRequest request,
                                       @PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return allowanceService.addAllowance(request,employeeId);
    }

    @GetMapping(value = "/employees/{employee-id}/allowances",headers = "version=v1")
    public List<AllowanceResponse> get(@PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return allowanceService.getAllowance(employeeId);
    }

    @PutMapping(value = "/employees/{employee-id}/allowances/{allowance-id}",headers = "version=v1")
    public CreateAllowanceResponse update(@RequestBody CreateAllowanceRequest request,
                       @PathVariable ("employee-id")Long employeeId,
                       @PathVariable ("allowance-id")Long allowanceId) throws EmployeeNotFoundException, AllowanceNotFoundException {
        return allowanceService.updateAllowance(request,employeeId,allowanceId);
    }

    @DeleteMapping(value = "/employees/{employee-id}/allowances/{allowance-id}",headers = "version=v1")
    public CreateAllowanceResponse delete(@PathVariable ("employee-id")Long employeeId,
                       @PathVariable ("allowance-id")Long allowanceId) throws EmployeeNotFoundException, AllowanceNotFoundException {
        return allowanceService.deleteAllowance(employeeId,allowanceId);
    }
}
