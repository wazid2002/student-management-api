package com.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.college.dto.EnrollmentRequest;
import com.college.dto.StudentResponse;
import com.college.entity.Course;
import com.college.entity.Student;
import com.college.mapper.StudentMapper;
import com.college.repository.CourseRepository;
import com.college.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
	
	public StudentService(StudentRepository studentRepository,CourseRepository courseRepository) {
		this.courseRepository=courseRepository;
		this.studentRepository=studentRepository;
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public StudentResponse getStudentById(Long id) {
		
		Student student=studentRepository.findById(id).orElse(null);
		
		if(student == null) {
			return null;
		}
		
		return StudentMapper.toResponse(student);
	}
	
	public Student createStudent(Student Student) {
		return studentRepository.save(Student);
	}
	
	public Student updateStudent(Long id,Student student) {
		Student existingStudent = studentRepository.findById(id).orElse(null);
		
		if (existingStudent == null) {
			return null;
		}
		
		existingStudent.setName(student.getName());
		existingStudent.setAge(student.getAge());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setCourse(student.getCourse());
		
		return studentRepository.save(existingStudent);	
	}
	
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}
	
	public List<Student> getByCourse(String course){
		return studentRepository.findByCourse(course);
	}
	
	public List<Student> getByCourseAndAge(String course,int age){
		return studentRepository.findByCourseAndAge(course, age);
	}
	
	@Transactional
	public StudentResponse enrollStudent(EnrollmentRequest request) {
		
		Course course=courseRepository.findById(request.getCourseId()).orElseThrow(()-> new RuntimeException("Course not found"));
		
		if(course.getIntake() <= 0) {
			throw new RuntimeException("Course is full");
		}
		
		Student student = new Student();
		student.setName(request.getName());
		student.setAge(request.getAge());
		student.setEmail(request.getEmail());
		student.setCourse(course);
		
		course.setIntake(course.getIntake()-1);
		
		courseRepository.save(course);
		
		Student createdStudent=studentRepository.save(student);
		
		//throw new RuntimeException("Testing transaction rollback");
		
		return StudentMapper.toResponse(createdStudent);
		
	}
	
	
}
