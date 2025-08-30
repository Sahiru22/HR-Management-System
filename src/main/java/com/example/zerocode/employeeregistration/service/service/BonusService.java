package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateBonusRequest;
import com.example.zerocode.employeeregistration.service.controller.response.BonusResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateBonusResponse;
import com.example.zerocode.employeeregistration.service.exception.BonusNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import java.util.List;

public interface BonusService {

  CreateBonusResponse addBonus(CreateBonusRequest request)
      throws EmployeeNotFoundException;

  List<BonusResponse> getBonus(Long employeeId) throws EmployeeNotFoundException;

  List<BonusResponse> getBonusById(Long bonusId) throws BonusNotFoundException;

  CreateBonusResponse updateBonus(CreateBonusRequest request, Long bonusId)
      throws EmployeeNotFoundException, BonusNotFoundException;

  CreateBonusResponse deleteBonus(Long bonusId)
      throws BonusNotFoundException;
}
