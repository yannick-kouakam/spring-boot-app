package com.example.demo.dao;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getEmployees();

    void saveEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    Employee findEmployee(Long employeeId);
}
