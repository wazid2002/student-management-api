package com.college.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.college.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
	
	List<Student> findByCourse(String Course); //derived method based on method name
	
	@Query(value="Select * from student where course= :course and age > :age" ,nativeQuery=true)
	List<Student> findByCourseAndAge(String course,int age);

}
