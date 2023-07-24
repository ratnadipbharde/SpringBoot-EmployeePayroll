package com.example.EmployeePayroll.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@EnableWebMvc
public class Config {

    @Bean
   public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
   public SimpleMailMessage mailMessage(){
        return new SimpleMailMessage();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) //url-http://localhost:8080/swagger-ui/index.html
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.EmployeePayroll.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
}
