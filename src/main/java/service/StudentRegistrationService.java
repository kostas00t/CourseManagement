package service;

import java.util.List;

public interface StudentRegistrationService {

	List<model.StudentRegistration> findRegistrationsByCourseId(int a);
	void delete(int a);
	void save(model.StudentRegistration a);
	void update(model.StudentRegistration a);
	
}
