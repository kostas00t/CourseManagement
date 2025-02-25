package cms.service;

import java.util.ArrayList;

import cms.entity.Course;

public interface CourseService {
	
	public ArrayList<Course> findCourseByInstructorLogin(String theInstructor);
	
	public Course findById(int theId);
	
	public void delete(int courseId, String instructor);
	
	public void save(Course theCourse);
	
	public void update(Course theCourse, String theInstructor);
	
}
