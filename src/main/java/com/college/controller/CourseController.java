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
import org.springframework.web.bind.annotation.RestController;

import com.college.entity.Course;
import com.college.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	private CourseService courseService;
	
	public CourseController(CourseService courseService) {
		this.courseService=courseService;
	}
	
	@GetMapping
	public List<Course> getAll(){
		return courseService.getCourses();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getById(@PathVariable Long id){
		
		Course course=courseService.getById(id);
		
		if(course == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(course);
		
	}
	
	@PostMapping
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		
		Course newCourse=courseService.addCourse(course);
		
		return ResponseEntity.status(201).body(newCourse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> editCourse(@PathVariable Long id, @RequestBody Course course){
		
		Course updatedCourse=courseService.editCourse(id, course);
		
		if( updatedCourse == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(updatedCourse);
			
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Course> deleteCourse(@PathVariable Long id){
		
		courseService.deleteCourse(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	

}
