package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDto;
import com.example.EmployeePayroll.dto.ResponseDto;
import com.example.EmployeePayroll.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    String addEmployee(EmployeeDto employeeDto);

    List<Employee> getAllEmployee();

    ResponseDto updateEmployeeById(EmployeeDto employeeDto, int id);

    ResponseDto deleteEmployeeById(int id);

    ResponseDto getEmployeeById(int id);
}
