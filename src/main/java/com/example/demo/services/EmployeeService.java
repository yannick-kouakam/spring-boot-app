package com.example.demo.services;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    void save(Employee employee);
    void deleteByI(Long id);
}
