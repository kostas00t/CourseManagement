package service;

import java.util.List;

public interface CourseService {
	
	List<model.Course> findCourseByInstructorLogin(String a);
	void delete(int a);
	void save(model.Course a);
	void update(model.Course a);
}
