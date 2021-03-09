package com.example.demo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    //create get all Employee Api
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    //create new employee
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return repository.save(employee);
    }

    //get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) throws ResourceNotFoundExceptionEmployees {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundExceptionEmployees("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    //update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId
            , @RequestBody Employee employeeDetails) throws ResourceNotFoundExceptionEmployees {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundExceptionEmployees("Employee not found for this id :: " + employeeId));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        repository.save(employee);
        return ResponseEntity.ok().body(employee);
    }

    //delete employee by id
    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") long employeeId) throws ResourceNotFoundExceptionEmployees {
        repository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundExceptionEmployees("Employee not found for this id :: " + employeeId));
        repository.deleteById(employeeId);
        return ResponseEntity.ok().build();
    }
}
