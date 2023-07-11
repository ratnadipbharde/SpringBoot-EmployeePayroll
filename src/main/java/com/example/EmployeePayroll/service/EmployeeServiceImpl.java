package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDto;
import com.example.EmployeePayroll.dto.LoginDto;
import com.example.EmployeePayroll.dto.LoginResponseDto;
import com.example.EmployeePayroll.dto.ResponseDto;
import com.example.EmployeePayroll.exception.CustomException;
import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.repository.EmployeeRepo;
import com.example.EmployeePayroll.utility.JwtUility;
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

    @Autowired
    private JwtUility jwtUility;

    @Override
    public String addEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepo.save(employee);
        return "added successfully...";
    }

    @Override
    public List<Employee> getAllEmployee(String token) {
        if (isAuthorized(token)) {
            return employeeRepo.findAll();
        }
        else {
            throw new CustomException("not authorised...");
        }
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
    public ResponseDto getEmployeeById(int id, String token) {
        if (isAuthorized(token)){
        if (isEmployeeExist(id)) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setObject(employeeRepo.findById(id));
            responseDto.setMessage("fetch data successfully...");
            return responseDto;
        } else {
            throw new CustomException("id not exist...");
        }}
        else {
            throw new CustomException("not authorised...");
        }
    }

    @Override
    public LoginResponseDto userLogin(LoginDto loginDto) {
        Optional<Employee> employee = Optional.ofNullable(employeeRepo.findByEmail(loginDto.getEmail()).orElse(null));
        if (employee.isPresent()) {
            if (employee.get().getPassword().equals(loginDto.getPassword())) {
                LoginResponseDto loginResponse = new LoginResponseDto();
                String token = jwtUility.generateToken(loginDto);
                loginResponse.setToken(token);
                if (!employee.get().isLogin()) {
                    loginResponse.setMessage("User logged in successfully...");
                    employee.get().setLogin(true);
                    employeeRepo.save(employee.get());
                }else {
                    loginResponse.setMessage("User already logged in...");
                }
                return loginResponse;
            } else {
                throw new CustomException("Invalid password.");
            }
        } else {
            throw new CustomException("Invalid email.");
       }
    }

    @Override
    public String userLogout(String token){
        LoginDto loginDto=jwtUility.decodeToken(token);
        Optional<Employee> employee=employeeRepo.findByEmail(loginDto.getEmail());
        employee.get().setLogin(false);
        employeeRepo.save(employee.get());
        return "logout successfully...";
    }

    @Override
    public LoginDto decodeToken(String token) {
        LoginDto loginDto=jwtUility.decodeToken(token);
        return loginDto;
    }


    public boolean isAuthorized(String token){
        LoginDto loginDto=jwtUility.decodeToken(token);
        Optional<Employee> optionalEmployee = Optional.ofNullable(employeeRepo.findByEmail(loginDto.getEmail()).orElse(null));
       Employee employee=optionalEmployee.get();
        return loginDto.getEmail().equals(employee.getEmail()) && loginDto.getPassword().equals(employee.getPassword());
        }


    }
