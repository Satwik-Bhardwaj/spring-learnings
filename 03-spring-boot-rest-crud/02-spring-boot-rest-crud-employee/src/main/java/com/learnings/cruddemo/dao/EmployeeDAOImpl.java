package com.learnings.cruddemo.dao;

import com.learnings.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    // define field for entityManager
    private EntityManager entityManager;

    // set up the constructor injection

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

        // execute a query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee WHERE id =:theId", Employee.class);
        theQuery.setParameter("theId", theId);

        Employee result = theQuery.getSingleResult();

        return result;
    }

    @Override
    public void deleteById(int theId) {
        Employee dbEmployee = entityManager.find(Employee.class, theId);

        if(dbEmployee == null) {
            throw new RuntimeException("Employee id not found - " + theId);
        }

        entityManager.remove(dbEmployee);
    }

    @Override
    public Employee save(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);

        return dbEmployee;
    }
}
