package com.example.EmployeePayroll.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Entity
@Data
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String password;
    private String name;
    private String gender;
    private List<String> department;
    private Long salary;
    private String startDate;
    private String notes;
    private String roll;
    private boolean isLogin;
}

