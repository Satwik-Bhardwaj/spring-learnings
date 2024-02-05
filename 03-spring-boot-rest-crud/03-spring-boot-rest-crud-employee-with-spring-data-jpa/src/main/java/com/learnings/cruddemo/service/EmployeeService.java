package com.learnings.cruddemo.service;

import com.learnings.cruddemo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();

    public Employee findById(int theId);

    public Employee save(Employee employee);

    public void deleteById(int theId);

}
