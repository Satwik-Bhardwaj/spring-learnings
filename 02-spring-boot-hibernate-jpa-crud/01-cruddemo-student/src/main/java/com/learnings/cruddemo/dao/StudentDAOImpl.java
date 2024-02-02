package com.learnings.cruddemo.dao;

import com.learnings.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent); // (learn it)
    }

    // implement findById method
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);   // (learn it)
    }

    // implementation of findByEmailType
    @Override
    public List<Student> findByEmailType(String site) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE email LIKE :site", Student.class);
        theQuery.setParameter("site", "%" + site);
        return theQuery.getResultList();
    }

    // implementation of findAll
    @Override
    public List<Student> findAllInDescOrder() {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student ORDER BY firstName DESC", Student.class);
        return theQuery.getResultList();
    }

    // implementation of update
    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    // implementation fo delete
    @Override
    @Transactional
    public void delete(int theId) {

        Student theStudent = entityManager.find(Student.class, theId);
        entityManager.remove(theStudent);
    }

    // implementation of deleteAll
    @Override
    @Transactional
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numRowsDeleted;
    }

}
