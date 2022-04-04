package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Course;
import model.StudentRegistration;
import service.CourseService;
import service.StudentRegistrationService;

@Controller
public class CoursesMgtAppController implements CourseService, StudentRegistrationService {

	@Override
	@RequestMapping(value = "/findRegistrationsByCourseId")
	public List<StudentRegistration> findRegistrationsByCourseId(int a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/addStudent")
	public void save(StudentRegistration a) {
		// TODO Auto-generated method stub

	}

	@Override
	@RequestMapping(value = "/updateStudent")
	public void update(StudentRegistration a) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Course> findCourseByInstructorLogin(String a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/delete")
	public void delete(int a) {
		// TODO Auto-generated method stub

	}

	@Override
	@RequestMapping(value = "/addCourse")
	public void save(Course a) {
		// TODO Auto-generated method stub

	}

	@Override
	@RequestMapping(value = "/updateCourse")
	public void update(Course a) {
		// TODO Auto-generated method stub

	}

}
