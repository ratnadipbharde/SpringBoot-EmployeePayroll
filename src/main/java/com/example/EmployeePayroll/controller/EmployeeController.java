package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeeDto;
import com.example.EmployeePayroll.dto.ResponseDto;
import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.addEmployee(employeeDto);
    }

    @PostMapping("/getEmployeeById")
    public ResponseDto getEmployeeById(@RequestParam int id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/updateEmployeeById")
    public ResponseDto updateEmployeeById(@RequestBody EmployeeDto employeeDto, @RequestParam int id) {
        return employeeService.updateEmployeeById(employeeDto, id);
    }

    @DeleteMapping("/deleteEmployeeById")
    public ResponseDto deleteEmployeeById(@RequestParam int id){
        return employeeService.deleteEmployeeById(id);
    }
}
