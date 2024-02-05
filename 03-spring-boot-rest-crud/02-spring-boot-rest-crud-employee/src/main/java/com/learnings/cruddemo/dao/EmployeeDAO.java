package com.learnings.cruddemo.dao;

import com.learnings.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

//    this will return all the employee
    List<Employee> findAll();

//    this saves the employees
    Employee save(Employee employee);

//    this will return an employee of the same id
    Employee findById(int theId);

    void deleteById(int theId);
}
