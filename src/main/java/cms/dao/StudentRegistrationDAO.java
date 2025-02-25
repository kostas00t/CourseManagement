package cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cms.entity.Course;
import cms.entity.StudentRegistration;

@Repository
public interface StudentRegistrationDAO extends JpaRepository<StudentRegistration, Integer> {
	
	@Query("select s from StudentRegistration s where s.studentID = ?1 and s.courseID = ?2")
	public StudentRegistration findById(int theId, Course course);
	
	@Query("select s from StudentRegistration s where s.courseID = ?1")
	public List<StudentRegistration> findByCourseId(Course course);

	@Modifying
	@Query("delete from StudentRegistration where studentId = ?1 and courseId = ?2")
	public void delete(int studentId, Course course);

	@Modifying
	@Query("update StudentRegistration u set u.name = ?1, u.yearofregistration = ?2, u.projectgrade = ?3, u.examgrade = ?4 where u.studentID = ?5 and u.courseID = ?6")
	public void save(String name, int yearofregistration, int projectgrade, int examgrade, int theStudentReg, Course course);
	
	@Modifying
	@Query("update StudentRegistration u set u.name = ?1, u.yearofregistration = ?2 where u.studentID = ?3")
	public void updateStudent(String name, int yearofregistration, int theStudentReg);
	
	@Modifying
	@Query("update StudentRegistration u set u.projectgrade = ?1, u.examgrade = ?2 where u.studentID = ?3 and u.courseID = ?4")
	public void updateGrade(int projectgrade, int examgrade, int theStudentReg, Course course);
	
	
	
}

