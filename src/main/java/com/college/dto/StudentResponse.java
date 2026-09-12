package com.college.dto;

public class StudentResponse {
	
	private String name;
	private String email;
	private int age;
	private String courseName;
	
	public StudentResponse() {
		
	}
	
	public StudentResponse(String name, String email, int age, String courseName) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
		this.courseName = courseName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	

}
