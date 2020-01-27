package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
  @Autowired TestRestTemplate restTemplate;

  @Mock EmployeeService employeeService;

  @Test
  public void testListEmployeeIsNotEmpty() {
    Mockito.when(employeeService.findAll())
        .thenReturn(Arrays.asList(new Employee(), new Employee()));
    ResponseEntity<List> forEntity = restTemplate.getForEntity("/employees/all", List.class);

    assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(forEntity.getBody()).isNotEmpty();
  }

  @Test
  public void getEmployeeShouldReturnASingleObject() {
    ResponseEntity<EmployeeDTO> forEntity =
        restTemplate.getForEntity("/employees/1", EmployeeDTO.class);
    assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(forEntity.getBody().getFirstName()).isEqualTo("yannick");
  }

  @Test
  public void getEmployeeWithWrongIdShouldReturnNotFound() {
    ResponseEntity<EmployeeDTO> forEntity =
        restTemplate.getForEntity("/employees/10", EmployeeDTO.class);
    assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}
