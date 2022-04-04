package dao;

import java.util.List;

public interface CourseDAO {

	List<model.Course> findCourseByInstructorLogin(String a);
	void delete(int a);
	void save(model.Course a);
	void update(model.Course a);
	
}
