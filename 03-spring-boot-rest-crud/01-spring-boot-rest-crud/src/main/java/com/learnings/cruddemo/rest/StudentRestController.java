package com.learnings.cruddemo.rest;

import com.learnings.cruddemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;
    // define @PostConstruct to load the student data ... only once!
    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Shubh", "Saini"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }

    // define endpoint for "/student" - return list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

    // define endpoint or "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {

        // just index into the list ... keep it simple for now

        if( studentId >= theStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }
}
