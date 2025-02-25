package cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cms.entity.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer> {
	
	public Course findById(int theId);
	
	@Query("select i from Course i where i.instructor = ?1")
	public List<Course> findInstructor(String instructor);
	
	@Modifying
	@Query("update Course c set c.name = ?1, c.description = ?2, c.year = ?3, c.semester = ?4 where c.courseID = ?5 and c.instructor = ?6")
	public void update(String name, String description, int year, String semester, int courseID, String instructor);
		
	@Modifying
	@Query("delete from Course where courseId = ?1 and instructor = ?2")
	public void delete(int courseId, String instructor);
	
}
