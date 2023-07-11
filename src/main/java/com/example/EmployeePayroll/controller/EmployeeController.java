package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeeDto;
import com.example.EmployeePayroll.dto.LoginDto;
import com.example.EmployeePayroll.dto.LoginResponseDto;
import com.example.EmployeePayroll.dto.ResponseDto;
import com.example.EmployeePayroll.exception.CustomException;
import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/getAllEmployee")
    public List<Employee> getAllEmployee(@RequestHeader String token) {
        return employeeService.getAllEmployee(token);
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.addEmployee(employeeDto);
    }

    @PostMapping("/getEmployeeById")
    public ResponseDto getEmployeeById(@RequestParam int id, @RequestHeader(required = false) String token){
        if (token!=null) {
            return employeeService.getEmployeeById(id, token);
        }else {
            throw new CustomException("not autorised...");
        }
    }

    @PutMapping("/updateEmployeeById")
    public ResponseDto updateEmployeeById(@RequestBody EmployeeDto employeeDto, @RequestParam int id) {
        return employeeService.updateEmployeeById(employeeDto, id);
    }

    @DeleteMapping("/deleteEmployeeById")
    public ResponseDto deleteEmployeeById(@RequestParam int id){
        return employeeService.deleteEmployeeById(id);
    }

    @PostMapping("/login")
    public LoginResponseDto userLogin(@RequestBody LoginDto loginDto){
        return employeeService.userLogin(loginDto);
    }

    @PostMapping("/logout")
    public String userLogout(@RequestHeader String token){
        return employeeService.userLogout(token);
    }

    @PostMapping("/decodeToken")
    public LoginDto decodeToken(@RequestHeader String token){
        return employeeService.decodeToken(token);
    }

}
