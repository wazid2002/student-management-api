package com.college.mapper;

import com.college.dto.StudentResponse;
import com.college.entity.Student;

public class StudentMapper {
	
	/*We're making it static because this mapper currently has
	no state and no dependencies.*/
	public static StudentResponse toResponse(Student student) {
		return new StudentResponse(
				student.getName(),
				student.getEmail(),
				student.getAge(),
				student.getCourse().getName());
	}

}
