package com.mynotes.springcloud.contract.producer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {

    private static List<Employee> empList = new ArrayList<>();

    static {
        empList.add(new Employee(1, "Max","Payne", 54000.0,"M"));
        empList.add(new Employee(2, "Bob","Martin", 73000.0,"M"));
        empList.add(new Employee(3, "Amy","Flower", 64000.0,"F"));
    }

    public Optional<Employee> findById(Integer id){
        return empList.stream().filter(emp -> emp.id.equals(id)).findFirst();
    }
}