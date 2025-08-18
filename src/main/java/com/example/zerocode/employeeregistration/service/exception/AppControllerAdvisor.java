package com.example.zerocode.employeeregistration.service.exception;

import com.example.zerocode.employeeregistration.service.controller.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdvisor {

  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler({DepartmentNotFoundException.class, EmployeeNotFoundException.class,
      AllowanceNotFoundException.class, BonusNotFoundException.class,
      DependentDetailsNotFoundException.class, EducationQualificationNotFoundException.class,
      EmergencyContactNotFoundException.class, EmployeeDesignationNotFoundException.class,
      InsuranceNotFoundException.class, IssuedItemNotFoundException.class,
      LeaveNotFoundException.class, SalaryNotFoundException.class,
      WorkHistoryNotFoundException.class})
  public ErrorResponse handleNotFoundException(Exception exception) {
    System.out.println("exception occurred : " + exception.getMessage());

    ErrorResponse response = new ErrorResponse();
    response.setMessage("bad request");
    return response;
  }
}
