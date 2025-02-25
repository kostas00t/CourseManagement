package cms.service;

import java.util.ArrayList;

import cms.entity.Course;
import cms.entity.StudentRegistration;

public interface StudentRegistrationService {
	
	ArrayList<StudentRegistration> findRegistrationsByCourseId(Course course);
	
	public StudentRegistration findById(int theId, Course theCourse);
	
	public void delete(int studentId, Course course);
	
	public void add(StudentRegistration theStudentReg);

	public void updateStudent(StudentRegistration theStudentReg);

	public void updateGrade(StudentRegistration theStudentReg, Course course);

}
