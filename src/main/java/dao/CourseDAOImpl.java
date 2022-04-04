package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.Course;

public class CourseDAOImpl implements CourseDAO {
	
	private EntityManager entityManager;

	public CourseDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Course> findCourseByInstructorLogin(String a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Course a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Course a) {
		// TODO Auto-generated method stub
		
	}

}
