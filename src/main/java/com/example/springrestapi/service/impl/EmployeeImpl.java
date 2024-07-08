package com.example.springrestapi.service.impl;

import com.example.springrestapi.dto.request.AuthenticationRequest;
import com.example.springrestapi.dto.request.EmployeeSaveRequest;
import com.example.springrestapi.dto.request.EmployeeUpdateRequest;
import com.example.springrestapi.dto.response.AuthenticationResponse;
import com.example.springrestapi.dto.response.EmployeeResponse;
import com.example.springrestapi.entity.Employee;
import com.example.springrestapi.exception.AppException;
import com.example.springrestapi.exception.ErrorCode;
import com.example.springrestapi.mapper.EmployeeMapper;
import com.example.springrestapi.repostirory.EmployeeRepostirory;
import com.example.springrestapi.service.EmployeeService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.NonFinal;
import org.mapstruct.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
// không cần Autowired và private
//@RequiredArgsConstructor(onConstructor_ = {@Autowired})
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeImpl implements EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeImpl.class);
    @Autowired
    EmployeeRepostirory employeeRepos;

    @Autowired
    private EmployeeMapper employeeMapper;

    @NonFinal // không vào contructer
    protected static final String SIGNER_KEY = "mpssgy1beenblsep7kvyr8pxvef3ruav";

    @Override
    public List<EmployeeResponse> findAll() {
        List<Employee> employees = employeeRepos.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (Employee employee : employees) {
            employeeResponses.add(employeeMapper.toEmployeeResponse(employee));
        }
        return employeeResponses;
    }

    @Override
    public EmployeeResponse findById(int id) {
        Employee employee = employeeRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("No found"));
        return employeeMapper.toEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse save(EmployeeSaveRequest request) {
        if(employeeRepos.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        Employee employee = employeeMapper.toEmployee(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        return employeeMapper.toEmployeeResponse(employeeRepos.save(employee));
    }

    @Override
    public EmployeeResponse update(EmployeeUpdateRequest request) {
        Employee employee = employeeRepos.getReferenceById(request.getId());
        employeeMapper.updateEmployee(employee, request);
        return employeeMapper.toEmployeeResponse(employeeRepos.save(employee));
    }

    @Override
    public boolean deleteById(int id) {
        if(employeeRepos.existsById(id)) {
            employeeRepos.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {
        Employee employee = employeeRepos.findByUsername(authenticationRequest.getUsername());
        if(employee != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(authenticationRequest.getPassword(), employee.getPassword())) {
                String token = generateToken(employee.getName());
                return new AuthenticationResponse().builder()
                        .token(token)
                        .build();
            }
            throw new AppException(ErrorCode.LOGIN_PASSWORD_FAILED);
        }
        throw new AppException(ErrorCode.LOGIN_USER_FAILED);
    }
    public String generateToken(String name){
        JWSHeader header =  new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimNames = new JWTClaimsSet.Builder()
                .subject(name)
                .issuer("devteria.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        // tạo ra token hạn sử dụng 1 giờ
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customClaim", "custom")
                .build();
        Payload payload = new Payload(jwtClaimNames.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        // Ký
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }

}


