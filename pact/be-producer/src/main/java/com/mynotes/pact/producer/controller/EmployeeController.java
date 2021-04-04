package com.mynotes.pact.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import javax.validation.Valid;

import com.mynotes.pact.producer.dto.Employee;
import com.mynotes.pact.producer.exceptions.EntityNotFoundException;
import com.mynotes.pact.producer.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/find")
    public List<Employee> employee() {

        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee employee(@PathVariable int id) throws EntityNotFoundException {

        return employeeService.findEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody Employee employee) throws IllegalAccessException, JsonProcessingException {

        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @Valid @RequestBody Employee employee)
            throws EntityNotFoundException, JsonProcessingException {

        employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws EntityNotFoundException, JsonProcessingException {

        employeeService.deleteEmployee(id);
    }


}