package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateAllowanceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.AllowanceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateAllowanceResponse;
import com.example.zerocode.employeeregistration.service.dto.AllowanceBasicDTO;
import com.example.zerocode.employeeregistration.service.exception.AllowanceNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.QAllowance;
import com.example.zerocode.employeeregistration.service.repository.AllowanceRepository;
import com.example.zerocode.employeeregistration.service.service.AllowanceService;
import com.querydsl.core.types.Predicate;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AllowanceController {

  private final AllowanceService allowanceService;
  private final AllowanceRepository allowanceRepository;

  @PostMapping(value = "/allowances", headers = "version=v1")
  public CreateAllowanceResponse add(@Valid @RequestBody CreateAllowanceRequest request)
      throws EmployeeNotFoundException {
    return allowanceService.addAllowance(request);
  }

  @GetMapping(value = "/allowances", headers = "version=v1")
  public Page<AllowanceBasicDTO> findAllowances(
      @RequestParam(name = "q", required = false) String q,
      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
      Pageable pageable) {
    Predicate predicate = QAllowance.allowance.id.isNotNull();
    if (StringUtils.isNotEmpty(q)) {
      predicate = QAllowance.allowance.allowanceType.containsIgnoreCase(q)
          .or(QAllowance.allowance.employee.firstName.containsIgnoreCase(q))
          .or(QAllowance.allowance.employee.lastName.containsIgnoreCase(q))
          .and(predicate);
    }
    if (date != null) {
      predicate = QAllowance.allowance.allowanceDate.loe(date)
          .and(predicate);
    }
    Pageable pg = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
        pageable.getSortOr(Sort.by(Sort.Direction.DESC, "id")));

    return allowanceRepository.findBy(predicate,
        f -> f.as(AllowanceBasicDTO.class).page(pg));
  }

  @GetMapping(value = "/employees/{employee-id}/allowances", headers = "version=v1")
  public List<AllowanceResponse> get(@PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException {
    return allowanceService.getAllowance(employeeId);
  }

  @GetMapping(value = "/allowances/{allowance-id}", headers = "version=v1")
  public List<AllowanceResponse> getById(
      @PathVariable("allowance-id") Long allowanceId)
      throws AllowanceNotFoundException {
    return allowanceService.getAllowanceById(allowanceId);
  }

  @PutMapping(value = "/allowances/{allowance-id}", headers = "version=v1")
  public CreateAllowanceResponse update(@Valid @RequestBody CreateAllowanceRequest request,
      @PathVariable("allowance-id") Long allowanceId)
      throws EmployeeNotFoundException, AllowanceNotFoundException {
    return allowanceService.updateAllowance(request, allowanceId);
  }

  @DeleteMapping(value = "/allowances/{allowance-id}", headers = "version=v1")
  public CreateAllowanceResponse delete(
      @PathVariable("allowance-id") Long allowanceId)
      throws AllowanceNotFoundException {
    return allowanceService.deleteAllowance(allowanceId);
  }
}
