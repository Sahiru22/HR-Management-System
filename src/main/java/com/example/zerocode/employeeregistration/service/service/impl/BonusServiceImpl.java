package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateBonusRequest;
import com.example.zerocode.employeeregistration.service.controller.response.BonusResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateBonusResponse;
import com.example.zerocode.employeeregistration.service.exception.BonusNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Bonus;
import com.example.zerocode.employeeregistration.service.model.Employee;
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
  public CreateBonusResponse addBonus(CreateBonusRequest request)
      throws EmployeeNotFoundException {
    log.info("successfully adding bonus by employee id: {}", request.getEmployeeId());

    var bonus = new Bonus();
    modelMapper.map(request, bonus);

    if (request.getEmployeeId() != null) {
      Employee employee = employeeRepository.findById(request.getEmployeeId())
          .orElseThrow(() -> new EmployeeNotFoundException(
              "Not found employee with id:" + request.getEmployeeId()));
      bonus.setEmployee(employee);
    }
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
  public List<BonusResponse> getBonusById(Long bonusId) throws BonusNotFoundException {
    log.info("Getting bonus with bonus Id: {}", bonusId);

    var bonus = bonusRepository.findById(bonusId)
        .orElseThrow(() -> new BonusNotFoundException("Not found bonus with id:" + bonusId));

    return List.of(BonusResponse.builder()
        .id(bonus.getId())
        .bonusType(bonus.getBonusType())
        .bonusAmount(bonus.getBonusAmount())
        .bonusDate(bonus.getBonusDate())
        .build());
  }

  @Override
  public CreateBonusResponse updateBonus(CreateBonusRequest request, Long bonusId)
      throws EmployeeNotFoundException, BonusNotFoundException {
    log.info("update bonus with employee id : {}", request.getEmployeeId());

    var bonus = bonusRepository.findById(bonusId)
        .orElseThrow(() -> new BonusNotFoundException("Not found bonus with id:" + bonusId));

    modelMapper.map(request, bonus);

    if (request.getEmployeeId() != null) {
      Employee employee = employeeRepository.findById(request.getEmployeeId())
          .orElseThrow(() -> new EmployeeNotFoundException(
              "Not found employee with id:" + request.getEmployeeId()));
      bonus.setEmployee(employee);
    }
    bonusRepository.save(bonus);

    var response = new CreateBonusResponse();
    response.setId(bonus.getId());

    return response;
  }

  @Override
  public CreateBonusResponse deleteBonus(Long bonusId)
      throws BonusNotFoundException {
    log.info("delete bonus by id : {}", bonusId);

    var bonus = bonusRepository.findById(bonusId)
        .orElseThrow(() -> new BonusNotFoundException("Not found bonus with id:" + bonusId));

    bonusRepository.delete(bonus);

    var response = new CreateBonusResponse();
    response.setId(bonus.getId());

    return response;

  }
}
