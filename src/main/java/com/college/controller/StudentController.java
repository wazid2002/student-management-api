package com.college.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.EnrollmentRequest;
import com.college.dto.StudentResponse;
import com.college.entity.Student;
import com.college.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService=studentService;
	}
	
	@GetMapping
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();	
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Student>> getByCourse(@RequestParam("course") String course){
		
		List<Student> students=studentService.getByCourse(course);
		
		if(students.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(students);
	}
	
	@GetMapping("/search-by-course-age")
	public ResponseEntity<List<Student>> getByCourseAndAge(@RequestParam("course") String course,
															@RequestParam("age") int age){
		List<Student> students=studentService.getByCourseAndAge(course, age);
		
		if(students.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(students);
	}
	
	@PostMapping
	public ResponseEntity<Student> saveToDB(@RequestBody Student student) {
		
		Student savedStudent=studentService.createStudent(student);
		
		return ResponseEntity.status(201).body(savedStudent);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id) {
		
		StudentResponse student=studentService.getStudentById(id);
		
		if (student == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(student);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id , @RequestBody Student student) {
		
		Student updatedStudent=studentService.updateStudent(id, student);
		
		if (updatedStudent == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(updatedStudent);
				
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}
	
	@PostMapping("/enroll")
	public ResponseEntity<StudentResponse> enrollStudent(@RequestBody EnrollmentRequest request){
		StudentResponse studentResponse=studentService.enrollStudent(request);
		return ResponseEntity.status(201).body(studentResponse);
	}
	

}
