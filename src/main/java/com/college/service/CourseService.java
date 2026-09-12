package com.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.college.entity.Course;
import com.college.repository.CourseRepository;

@Service
public class CourseService {
	
	private CourseRepository courseRepository;
	
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> getCourses(){
		return courseRepository.findAll();
	}
	
	public Course getById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}
	
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}
	
	public Course editCourse(Long id,Course course) {
		Course existingCourse=courseRepository.findById(id).orElse(null);
		
		if(existingCourse == null) {
			return null;
		}
		
		existingCourse.setName(course.getName());
		existingCourse.setIntake(course.getIntake());
		
		return courseRepository.save(existingCourse);
	}
	
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}

}
