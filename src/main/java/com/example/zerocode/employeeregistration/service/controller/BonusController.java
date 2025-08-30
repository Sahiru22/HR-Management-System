package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateBonusRequest;
import com.example.zerocode.employeeregistration.service.controller.response.BonusResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateBonusResponse;
import com.example.zerocode.employeeregistration.service.dto.BonusBasicDTO;
import com.example.zerocode.employeeregistration.service.exception.BonusNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.QBonus;
import com.example.zerocode.employeeregistration.service.repository.BonusRepository;
import com.example.zerocode.employeeregistration.service.service.BonusService;
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
public class BonusController {

  private final BonusService bonusService;
  private final BonusRepository bonusRepository;

  @PostMapping(value = "/bonuses", headers = "version=v1")
  public CreateBonusResponse add(@Valid @RequestBody CreateBonusRequest request)
      throws EmployeeNotFoundException {
    return bonusService.addBonus(request);
  }

  @GetMapping(value = "/bonuses", headers = "version=v1")
  public Page<BonusBasicDTO> findBonuses(
      @RequestParam(name = "q", required = false) String q,
      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
      Pageable pageable) {
    Predicate predicate = QBonus.bonus.id.isNotNull();
    if (StringUtils.isNotEmpty(q)) {
      predicate = QBonus.bonus.bonusType.containsIgnoreCase(q)
          .or(QBonus.bonus.employee.firstName.containsIgnoreCase(q))
          .or(QBonus.bonus.employee.lastName.containsIgnoreCase(q))
          .and(predicate);
    }
    if (date != null) {
      predicate = QBonus.bonus.bonusDate.loe(date)
          .and(predicate);
    }
    Pageable pg = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
        pageable.getSortOr(Sort.by(Sort.Direction.DESC, "id")));

    return bonusRepository.findBy(predicate,
        f -> f.as(BonusBasicDTO.class).page(pg));
  }

  @GetMapping(value = "/employees/{employee-id}/bonuses", headers = "version=v1")
  public List<BonusResponse> get(@PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException {
    return bonusService.getBonus(employeeId);
  }

  @GetMapping(value = "/bonuses/{bonus-id}", headers = "version=v1")
  public List<BonusResponse> getById(
      @PathVariable("bonus-id") Long bonusId)
      throws BonusNotFoundException {
    return bonusService.getBonusById(bonusId);
  }

  @PutMapping(value = "/bonuses/{bonus-id}", headers = "version=v1")
  public CreateBonusResponse update(@Valid @RequestBody CreateBonusRequest request,
      @PathVariable("bonus-id") Long bonusId)
      throws BonusNotFoundException, EmployeeNotFoundException {
    return bonusService.updateBonus(request, bonusId);
  }

  @DeleteMapping(value = "/bonuses/{bonus-id}", headers = "version=v1")
  public CreateBonusResponse delete(
      @PathVariable("bonus-id") Long bonusId)
      throws BonusNotFoundException {
    return bonusService.deleteBonus(bonusId);
  }
}
