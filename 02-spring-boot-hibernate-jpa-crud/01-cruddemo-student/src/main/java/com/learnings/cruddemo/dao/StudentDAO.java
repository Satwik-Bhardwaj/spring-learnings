package com.learnings.cruddemo.dao;

import com.learnings.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findByEmailType(String siteDotCom);

    List<Student> findAllInDescOrder();

    void update(Student theStudent);

    void delete(int theId);

    int deleteAll();
}
