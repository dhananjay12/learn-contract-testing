package com.mynotes.pact.producer.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.mynotes.pact.producer.dto.Employee;
import com.mynotes.pact.producer.exceptions.EntityNotFoundException;
import com.mynotes.pact.producer.exceptions.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    //Mimicking DB ...Not Fully Thread safe
    private static final AtomicInteger count = new AtomicInteger(0);
    private Map<Integer, Employee> employeeMap = new ConcurrentHashMap<>();

    public List<Employee> findAll() {
        return employeeMap.values().stream()
                .collect(Collectors.toList());
    }

    public Employee findEmployeeById(int id) throws EntityNotFoundException {
        return checkEmployee(id);
    }

    public Employee saveEmployee(Employee employee) throws JsonProcessingException {

        for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
            if (entry.getValue().getEmail().equalsIgnoreCase(employee.getEmail())) {
                throw new ValidationException("Email already registered");
            }
        }
        int id = count.incrementAndGet();
        employee.setId(id);
        employeeMap.put(id, employee);

        return employee;

    }

    public void updateEmployee(int id, Employee employee) throws EntityNotFoundException, JsonProcessingException {
        checkEmployee(id);
        employeeMap.put(id, employee);
    }

    public void deleteEmployee(int id) throws EntityNotFoundException, JsonProcessingException {
        Employee employee = checkEmployee(id);
        employeeMap.remove(id);
    }

    private Employee checkEmployee(int id) throws EntityNotFoundException {
        Employee result = employeeMap.get(id);
        if (result == null) {
            throw new EntityNotFoundException("Could not find entity with id: " + id);
        }
        return result;
    }

}
