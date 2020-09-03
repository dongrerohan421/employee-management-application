package com.employee.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.employee.EmployeeManagementApplication;
import com.employee.model.Employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = EmployeeManagementApplication.class)
@ActiveProfiles("test")
public class EmployeeControllerTest {

    ArrayList<Employee> employees = new ArrayList<>();

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    EmployeeController employeeController;

    HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    public void setup() {
        employees.add(Employee.builder().id(1L).firstName("Rohan").lastName("Dongre").emailId("rd@gmail.com").build());
        employees.add(Employee.builder().id(2L).firstName("Andy").lastName("Moorey").emailId("am@gmail.com").build());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + randomServerPort + "/employee-management-application//api/v1/" + uri;
    }

    @Test
    public void createEmployee_returnsSuccessful_whenIdIsSet() throws Exception {

        Employee createEmployee = Employee.builder().firstName("Mary").lastName("Jane").emailId("mj@gmail.com")
                .firstName("Anuja").lastName("Dongre").emailId("ad@gmail.com").build();

        HttpEntity<Employee> entity = new HttpEntity<>(createEmployee, headers);
        ResponseEntity<String> actualResponse = this.restTemplate.exchange(createURLWithPort("employees"),
                HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void getEmployeeById_ReturnsSuccessful_IfFound() throws Exception {
        HttpEntity<Employee> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> actualResponse = this.restTemplate.exchange(createURLWithPort("employees/1"),
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());

    }

    @Test
    public void getAllEmployees_ReturnsSuccessful_IfFound() throws Exception {
        List<Employee> employees = employeeController.getAllEmployees();
        assertEquals(1, employees.size());
    }

    @Test
    public void testDeleteEmployeeById_ReturnsSuccessful_IfFound() throws Exception {
        ResponseEntity<Object> deleteEmployee = employeeController.deleteEmployee(1l);
        assertEquals(200, deleteEmployee.getStatusCodeValue());
    }
}