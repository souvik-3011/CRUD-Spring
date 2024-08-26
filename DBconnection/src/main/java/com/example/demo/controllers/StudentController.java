package com.example.demo.controllers;
import java.util.*;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class StudentController 
{
	@Autowired
	StudentRepository studentRepository;
	@GetMapping("/")
	public String running()
	{
		return "My Project is running";
	}
	
	// http://localhost:8080/api/add
	@PostMapping("/add")
	public String addStudent(@RequestBody Student student)
	{
		studentRepository.save(student);
		return "Student Saved Successfully";
	}
	//Getting all student (Read)
	@GetMapping("/allStudents")
	public ResponseEntity<List<Student>> getAllStudent(){
		List <Student> students = new ArrayList<>();
		studentRepository.findAll().forEach(students::add);
		return new ResponseEntity <List<Student>> (students,HttpStatus.OK);
		
	}
	//Delete
	@GetMapping("/delete/{roll}")
	public String studentDelete(@PathVariable int roll) {
		studentRepository.deleteById(roll);
		return "Student deleted succesfully" ;
		
	}
	//Update students
	@PutMapping("edit/{roll}")
		public String editStudent(@PathVariable int roll , @RequestBody Student student) 
		{
			
			Optional<Student> studentOption = studentRepository.findById(roll);
			if(studentOption.isPresent()) {
				Student existingStudent = studentOption.get();
				existingStudent.setAge(student.getAge());
				existingStudent.setName(student.getName());
				existingStudent.setMarks(student.getMarks());
				
				studentRepository.save(existingStudent);
				return "Student update successfully" ;
			}
			return "Student not found" ;
			
			
		}
	//deleteall
	
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		studentRepository.deleteAll();
		return "All students are delete" ;
	}
			
			
		

}

