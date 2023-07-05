package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDto;
import com.example.EmployeePayroll.dto.ResponseDto;
import com.example.EmployeePayroll.exception.CustomException;
import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepo.save(employee);
        return "added successfully...";
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public ResponseDto updateEmployeeById(EmployeeDto employeeDto, int id) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        if (isEmployeeExist(id)) {
            ResponseDto responseDto = new ResponseDto();
            employee.setId(id);
            responseDto.setObject(employeeDto);
            responseDto.setMessage("update successfully...");
            employeeRepo.save(employee);
            return responseDto;
        } else {
            throw new CustomException("id not exist...");
        }
    }

    private boolean isEmployeeExist(int id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseDto deleteEmployeeById(int id) {
        if (isEmployeeExist(id)) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setObject(employeeRepo.findById(id));
            employeeRepo.deleteById(id);
            responseDto.setMessage("employee data delete successfully...");
            return responseDto;
        } else {
            throw new CustomException("id not exist...");
        }
    }

    @Override
    public ResponseDto getEmployeeById(int id) {
        if (isEmployeeExist(id)) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setObject(employeeRepo.findById(id));
            responseDto.setMessage("fetch data successfully...");
            return responseDto;
        } else {
            throw new CustomException("id not exist...");
        }
    }

}
