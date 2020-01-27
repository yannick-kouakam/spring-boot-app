package com.example.demo.services.impl;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;
import com.example.demo.services.EmployeeService;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeDao employeeDao = Mockito.mock(EmployeeDao.class);
    private List<Employee> employeeList;
    private EmployeeService employeeService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeDao);
        Employee employee1 = new Employee();
        employee1.setId(1L);
        Employee employee2 = new Employee("Yannick", "Kouakam", "some@gmail.com");
        employee1.setId(2L);
        Employee employee3 = new Employee();
        employee1.setId(3);
        Employee employee4 = new Employee();
        employee1.setId(4);
        employeeList = Arrays.asList(employee1, employee2, employee3, employee4);
    }

    @org.junit.jupiter.api.Test
    void findAll() {
        Mockito.when(employeeDao.getEmployees()).thenReturn(employeeList);
        List<Employee> all = employeeService.findAll();
        assertIterableEquals(employeeList, all);
    }

    @org.junit.jupiter.api.Test
    void findById() {
        Mockito.when(employeeDao.findEmployee(2L)).thenReturn(employeeList.stream().filter(employee -> employee.getId() == 2L).findFirst().get());
        Employee employee = employeeService.findById(2L);
        assertEquals(employee.getFirstName(), "Yannick");
    }

    @org.junit.jupiter.api.Test
    void deleteByI() {
        fail("Not yet implemented");
    }
}