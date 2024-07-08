package com.example.springrestapi.controller;

import com.example.springrestapi.dto.request.ApiResponse;
import com.example.springrestapi.dto.request.AuthenticationRequest;
import com.example.springrestapi.dto.response.AuthenticationResponse;
import com.example.springrestapi.dto.response.EmployeeResponse;
import com.example.springrestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    ApiResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse check = employeeService.authentication(authenticationRequest);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(200);
        apiResponse.setResult(check);
        return apiResponse;
    }
}
