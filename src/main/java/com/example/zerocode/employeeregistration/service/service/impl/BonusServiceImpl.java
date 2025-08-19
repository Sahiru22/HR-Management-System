package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateBonusRequest;
import com.example.zerocode.employeeregistration.service.controller.response.BonusResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateBonusResponse;
import com.example.zerocode.employeeregistration.service.exception.BonusNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Bonus;
import com.example.zerocode.employeeregistration.service.repository.BonusRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.BonusService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BonusServiceImpl implements BonusService {

  private final BonusRepository bonusRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateBonusResponse addBonus(CreateBonusRequest request, Long employeeId)
      throws EmployeeNotFoundException {
    log.info("successfully adding bonus by employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id: " + employeeId));

    var bonus = new Bonus();
    modelMapper.map(request, bonus);
    bonus.setEmployee(employee);
    bonusRepository.save(bonus);

    var response = new CreateBonusResponse();
    response.setId(bonus.getId());

    return response;
  }

  @Override
  public List<BonusResponse> getBonus(Long employeeId) throws EmployeeNotFoundException {
    log.info("getting bonus with employeeId : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    List<Bonus> bonuses = bonusRepository.findByEmployee(employee);

    return bonuses.stream()
        .map(bonus -> BonusResponse.builder()
            .id(bonus.getId())
            .bonusType(bonus.getBonusType())
            .bonusAmount(bonus.getBonusAmount())
            .bonusDate(bonus.getBonusDate())
            .build())
        .toList();
  }

  @Override
  public CreateBonusResponse updateBonus(CreateBonusRequest request, Long employeeId, Long bonusId)
      throws EmployeeNotFoundException, BonusNotFoundException {
    log.info("update bonus by employee id : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var bonus = bonusRepository.findById(bonusId)
        .orElseThrow(() -> new BonusNotFoundException("Not found bonus with id:" + bonusId));

    modelMapper.map(request, bonus);
    bonusRepository.save(bonus);

    var response = new CreateBonusResponse();
    response.setId(bonus.getId());

    return response;
  }

  @Override
  public CreateBonusResponse deleteBonus(Long employeeId, Long bonusId)
      throws EmployeeNotFoundException, BonusNotFoundException {
    log.info("delete bonus by employee id : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var bonus = bonusRepository.findById(bonusId)
        .orElseThrow(() -> new BonusNotFoundException("Not found bonus with id:" + bonusId));

    bonusRepository.delete(bonus);

    var response = new CreateBonusResponse();
    response.setId(bonus.getId());

    return response;

  }
}
