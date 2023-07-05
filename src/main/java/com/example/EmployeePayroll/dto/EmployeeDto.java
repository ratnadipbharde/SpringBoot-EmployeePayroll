package com.example.EmployeePayroll.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDto {
    String name;
    String gender;
    List<String> department;
    Long salary;
    String startDate;
    String notes;
}
