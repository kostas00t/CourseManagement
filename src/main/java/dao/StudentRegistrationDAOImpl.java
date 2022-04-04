package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.StudentRegistration;

public class StudentRegistrationDAOImpl implements StudentRegistrationDAO {

	public StudentRegistrationDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	private EntityManager entityManager;
	
	@Override
	public List<StudentRegistration> findRegistrationsByCourseId(int a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(StudentRegistration a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(StudentRegistration a) {
		// TODO Auto-generated method stub
		
	}

}
