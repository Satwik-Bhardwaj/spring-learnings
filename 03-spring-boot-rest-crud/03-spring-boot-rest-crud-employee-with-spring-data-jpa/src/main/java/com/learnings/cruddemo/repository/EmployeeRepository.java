package com.learnings.cruddemo.repository;

import com.learnings.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... there is no need to write any code
}
