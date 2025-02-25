package cms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cms.dao.StudentRegistrationDAO;
import cms.entity.Course;
import cms.entity.StudentRegistration;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

	@Autowired
	private StudentRegistrationDAO studentRegRepository;
	
	public StudentRegistrationServiceImpl() {}
	
	@Autowired
	public StudentRegistrationServiceImpl(StudentRegistrationDAO theStudentRegRepository) {
		studentRegRepository = theStudentRegRepository;
	}
	
	
	@Override
	@Transactional
	public StudentRegistration findById(int theId, Course theCourse) {
		StudentRegistration result = studentRegRepository.findById(theId, theCourse);
		return result;
	}
		
	@Override
	public ArrayList<StudentRegistration> findRegistrationsByCourseId(Course course) {
		return (ArrayList<StudentRegistration>) studentRegRepository.findByCourseId(course);
	}

	@Override
	@Transactional
	public void delete(int studentId, Course course) {
		studentRegRepository.delete(studentId, course);
	}

	@Override
	@Transactional
	public void add(StudentRegistration theStudentReg) {
		studentRegRepository.save(theStudentReg);
	}
	
	@Override
	@Transactional
	public void updateStudent(StudentRegistration theStudentReg) {
		int studentId = theStudentReg.getStudentID();
		String name = theStudentReg.getName();
		int yor = theStudentReg.getYearofregistration();
		studentRegRepository.updateStudent(name, yor, studentId);
	}
	
	@Override
	@Transactional
	public void updateGrade(StudentRegistration theStudentReg, Course course) {
		int studentId = theStudentReg.getStudentID();
		int projectGrade = theStudentReg.getProjectgrade();
		int examGrade = theStudentReg.getExamgrade();
		System.out.println("projectGrade: " + projectGrade + " examGrade: " + examGrade);
		studentRegRepository.updateGrade(projectGrade, examGrade, studentId, course);
	}

}
