package com.example.EmployeePayroll.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;


@Configuration
public class Config {

    @Bean
   public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
   public SimpleMailMessage mailMessage(){
        return new SimpleMailMessage();
    }


}
