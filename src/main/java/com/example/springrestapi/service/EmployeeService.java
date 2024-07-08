package com.example.springrestapi.service;

import com.example.springrestapi.dto.request.AuthenticationRequest;
import com.example.springrestapi.dto.request.EmployeeSaveRequest;
import com.example.springrestapi.dto.request.EmployeeUpdateRequest;
import com.example.springrestapi.dto.response.AuthenticationResponse;
import com.example.springrestapi.dto.response.EmployeeResponse;
import com.example.springrestapi.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    public List findAll();
    public EmployeeResponse findById(int id);
    public EmployeeResponse save(EmployeeSaveRequest request);
    public EmployeeResponse update(EmployeeUpdateRequest employee);
    public boolean deleteById(int id);
    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest);
}
