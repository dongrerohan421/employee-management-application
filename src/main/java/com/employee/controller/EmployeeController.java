package com.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    private static final String EMPLOYEE_NOT_FOUND = "Employee not found for this id :: ";

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = "employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping(value = "employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@Valid @PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND + employeeId));

        return ResponseEntity.ok().body(employee);
    }

    @PostMapping(value = "employees")
    public Employee createEmployee(@Validated @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping(value = "employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
            @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setLastName(employeeDetails.getFirstName());

        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok().body(updatedEmployee);
    }

    @DeleteMapping(value = "employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}