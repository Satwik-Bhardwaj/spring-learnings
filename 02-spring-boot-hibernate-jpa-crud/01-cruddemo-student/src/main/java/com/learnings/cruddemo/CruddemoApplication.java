package com.learnings.cruddemo;

import com.learnings.cruddemo.dao.StudentDAO;
import com.learnings.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);

//			readStudent(studentDAO);

//			readStudentByEmail(studentDAO);

//			findAllStudentByOrder(studentDAO);

//			updateStudent(studentDAO);

//			deleteStudent(studentDAO);

//			deleteAllStudents(studentDAO);
		};

	}

	private void deleteAllStudents(StudentDAO studentDAO) {

		System.out.println("Deleting all students");
		int numRowDeleted = studentDAO.deleteAll();
		System.out.println("Numbers of the rows deleted " + numRowDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		// finding the student using id
		int theId = 2;

		System.out.println("Deleting the student with id " + theId);
		studentDAO.delete(theId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// finding the student using id
		int theId = 1;
		System.out.println("Getting the student with id " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display the student before the update
		System.out.println("Details of the student before the update \n" + myStudent);

		// change the first name of the student
		myStudent.setFirstName("Raghav");

		// update the student
		studentDAO.update(myStudent);

		// retrieving the student
		Student newStudent = studentDAO.findById(theId);

		// display the student
		System.out.println("Details of the student after the update \n" + newStudent);

	}

	private void findAllStudentByOrder(StudentDAO studentDAO) {
		// retrieve all the students
		System.out.println("Retrieving all the students");
		List<Student> studentList = studentDAO.findAllInDescOrder();

		// display all the students
		for (Student student : studentList) {
			System.out.println(student);
		}
	}

	private void readStudentByEmail(StudentDAO studentDAO) {

		// get the email site
		String site = "@ongraph.com";
		System.out.println("Site domain is: " + site);

		// create a student object
		System.out.printf("Creating new student object with site domain of %s...\n", site);
		Student tempStudent = new Student("Vishnu", "Sharma", "vishnu.sharma@ongraph.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// retrieve student object list
		List<Student> studentList = studentDAO.findByEmailType(site);

		// display the list
		for (Student student : studentList) {
			System.out.println(student);
		}

	}

	private void readStudent(StudentDAO studentDAO) {

		// create a student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);


		// retrieve student based ob the id
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display the student
		System.out.println("Found the student: " + myStudent);
	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Satwik", "Bhardwaj", "satwikbhardwaj@gmail.com");
		Student tempStudent2 = new Student("Saransh", "Chaturvedi", "saransh.chaturvedi@gmail.com");
		Student tempStudent3 = new Student("Shreya", "Jha", "shreya.jha@gmail.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
