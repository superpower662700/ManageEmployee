package com.example.springrestapi.repostirory;

import com.example.springrestapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepostirory extends JpaRepository<Employee, Integer> {
    public boolean existsByUsername(String username);
    public boolean existsById(Integer id);
}
