package dao;

import java.util.List;

public interface StudentRegistrationDAO {
	
	List<model.StudentRegistration> findRegistrationsByCourseId(int a);
	void delete(int a);
	void save(model.StudentRegistration a);
	void update(model.StudentRegistration a);
	
}
