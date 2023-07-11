package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDto;
import com.example.EmployeePayroll.dto.LoginDto;
import com.example.EmployeePayroll.dto.LoginResponseDto;
import com.example.EmployeePayroll.dto.ResponseDto;
import com.example.EmployeePayroll.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    String addEmployee(EmployeeDto employeeDto);

    List<Employee> getAllEmployee(String token);

    ResponseDto updateEmployeeById(EmployeeDto employeeDto, int id);

    ResponseDto deleteEmployeeById(int id);

    ResponseDto getEmployeeById(int id, String token);

    LoginResponseDto userLogin(LoginDto loginDto);

    LoginDto decodeToken(String token);

    String userLogout(String token);
}
