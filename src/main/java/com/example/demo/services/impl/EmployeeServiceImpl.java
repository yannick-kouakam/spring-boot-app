package com.example.demo.services.impl;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;
import com.example.demo.services.EmployeeService;
import com.example.demo.utils.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeDao employeeDao;

  public EmployeeServiceImpl(EmployeeDao employeeDao) {
    this.employeeDao = employeeDao;
  }

  @Override
  public List<Employee> findAll() {
    return employeeDao.getEmployees();
  }

  @Override
  public Employee findById(Long id) {
    Employee employee = employeeDao.findEmployee(id);
    if (employee == null) {
      throw new NotFoundException(404, String.format("Not employee with ID: %d was found", id));
    }
    return employee;
  }

  @Override
  public void save(Employee employee) {
    employeeDao.saveEmployee(employee);
  }

  @Override
  public void deleteByI(Long id) {
    Employee employee = findById(id);
    employeeDao.deleteEmployee(employee);
  }
}
