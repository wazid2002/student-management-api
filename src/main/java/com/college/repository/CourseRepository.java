package com.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{
	
}
