package com.example.EmployeePayroll.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private String message;

    CustomException() {
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
    }
}
