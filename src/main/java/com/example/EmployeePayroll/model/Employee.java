package com.example.EmployeePayroll.model;

import javax.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String password;
    private String name;
    private String gender;
    @ElementCollection
    private List<String> department;
    private Long salary;
    private String startDate;
    private String notes;
    private String roll;
    private boolean isLogin;
}
