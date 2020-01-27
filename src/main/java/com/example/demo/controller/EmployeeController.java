package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.services.EmployeeService;
import com.example.demo.utils.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    private ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/all")
    public List<Employee> fetchAllEmployees() {
        return employeeService.findAll();
    }

    @PostMapping("")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = this.modelMapper.map(employeeDTO, Employee.class);
        this.employeeService.save(employee);
        employeeDTO.setId(employee.getId());
        return employeeDTO;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteByI(id);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") long id) {
        return this.employeeService.findById(id);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException ex){
       return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
