//package com.example.zerocode.employeeregistration.service.service.impl;
//
//import com.example.zerocode.employeeregistration.service.controller.request.CreateBonusRequest;
//import com.example.zerocode.employeeregistration.service.controller.response.BonusResponse;
//import com.example.zerocode.employeeregistration.service.controller.response.CreateBonusResponse;
//import com.example.zerocode.employeeregistration.service.exception.BonusNotFoundException;
//import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
//import com.example.zerocode.employeeregistration.service.model.Bonus;
//import com.example.zerocode.employeeregistration.service.model.Employee;
//import com.example.zerocode.employeeregistration.service.repository.BonusRepository;
//import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
//import com.example.zerocode.employeeregistration.service.service.BonusService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//@AllArgsConstructor
//public class BonusServiceImpl implements BonusService {
//
//    private final BonusRepository bonusRepository;
//    private final EmployeeRepository employeeRepository;
//
//    @Override
//    public CreateBonusResponse addBonus(CreateBonusRequest request, Long employeeId) throws EmployeeNotFoundException {
//        System.out.println("successfully adding bonus by employee id :" + employeeId);
//
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id: " + employeeId));
//
//            Bonus bonus = new Bonus();
//
//            bonus.setId(request.getId());
//            bonus.setBonusType(request.getBonusType());
//            bonus.setBonusAmount(request.getBonusAmount());
//            bonus.setBonusDate(request.getBonusDate());
//
//            bonus.setEmployee(employee);
//
//            bonusRepository.save(bonus);
//
//            CreateBonusResponse response = new CreateBonusResponse();
//            response.setId(bonus.getId());
//
//            return response;
//    }
//
//    @Override
//    public List<BonusResponse> getBonus(Long employeeId) throws EmployeeNotFoundException {
//        System.out.println("getting bonus with employeeId : " + employeeId);
//
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));
//
//        List<Bonus> bonuses = bonusRepository.findByEmployee(employee);
//
//        return bonuses.stream()
//                .map(bonus -> BonusResponse.builder()
//                        .id(bonus.getId())
//                        .bonusType(bonus.getBonusType())
//                        .bonusAmount(bonus.getBonusAmount())
//                        .bonusDate(bonus.getBonusDate())
//                        .build())
//                .toList();
//    }
//
//    @Override
//    public CreateBonusResponse updateBonus(CreateBonusRequest request, Long employeeId, Long bonusId) throws EmployeeNotFoundException, BonusNotFoundException {
//        System.out.println("update bonus by employee id : " + employeeId);
//
//        employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));
//
//        Bonus bonus = bonusRepository.findById(bonusId)
//                        .orElseThrow(() -> new BonusNotFoundException("Not found bonus with id:" + bonusId));
//
//                bonus.setBonusType(request.getBonusType());
//                bonus.setBonusAmount(request.getBonusAmount());
//                bonus.setBonusDate(request.getBonusDate());
//
//                bonusRepository.save(bonus);
//
//                CreateBonusResponse response = new CreateBonusResponse();
//                response.setId(bonus.getId());
//
//                return response;
//    }
//
//    @Override
//    public CreateBonusResponse deleteBonus(Long employeeId, Long bonusId) throws EmployeeNotFoundException, BonusNotFoundException {
//        System.out.println("delete bonus by employee id : " + employeeId);
//
//        employeeRepository.findById(employeeId)
//                        .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));
//
//        Bonus bonus = bonusRepository.findById(bonusId)
//                        .orElseThrow(() -> new BonusNotFoundException("Not found bonus with id:" + bonusId));
//
//        bonusRepository.delete(bonus);
//
//        CreateBonusResponse response = new CreateBonusResponse();
//        response.setId(bonus.getId());
//
//        return response;
//
//    }
//
//}
