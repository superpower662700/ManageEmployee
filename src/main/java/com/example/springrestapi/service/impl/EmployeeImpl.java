package com.example.springrestapi.service.impl;

import com.example.springrestapi.entity.Employee;
import com.example.springrestapi.repostirory.EmployeeRepostirory;
import com.example.springrestapi.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeImpl.class);
    @Autowired
    EmployeeRepostirory employeeRepos;

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeRepos.findAll();
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = employeeRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("No found"));
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        if(employeeRepos.existsByUsername(employee.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        return employeeRepos.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        Employee e = employeeRepos.getReferenceById(employee.getId());
        e.setName(employee.getName());
        e.setAddress(employee.getAddress());
        e.setPassword(employee.getPassword());
        System.out.println(e);
        return employeeRepos.save(e);
    }

    @Override
    public boolean deleteById(int id) {
        if(employeeRepos.existsById(id)) {
            employeeRepos.deleteById(id);
            return true;
        }
        return false;
    }
}
