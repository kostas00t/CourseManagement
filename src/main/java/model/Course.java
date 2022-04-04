package model;

import java.util.ArrayList;

public class Course {

	private int courseID;
	private String name;
	private String description;
	private int year;
	private String semester;
	private String instructor;
	private ArrayList<StudentRegistration> studentRegistrations;
	
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public ArrayList<StudentRegistration> getStudentRegistrations() {
		return studentRegistrations;
	}
	public void setStudentRegistrations(ArrayList<StudentRegistration> studentRegistrations) {
		this.studentRegistrations = studentRegistrations;
	}
	
	
}
