package com.example.EmployeePayroll.utility;

import java.util.HashMap;

import com.example.EmployeePayroll.dto.LoginDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUitility {
    final String SIGNATURE = "ratnadip";
    @Autowired
    LoginDto loginDto;

    @Autowired
    private ModelMapper modelMapper;

    public String generateToken(LoginDto loginDto) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("email", loginDto.getEmail());
        claims.put("password", loginDto.getPassword());
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SIGNATURE)
                .compact();
    }

    public LoginDto decodeToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SIGNATURE)
                .parseClaimsJws(token)
                .getBody();
        return modelMapper.map(claims, LoginDto.class);
    }
}
