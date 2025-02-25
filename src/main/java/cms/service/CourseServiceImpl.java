package cms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cms.dao.CourseDAO;
import cms.entity.Course;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDAO courseRepository;
	
	public CourseServiceImpl() {}

	@Autowired
	public CourseServiceImpl(CourseDAO theCourseRepository) {
		courseRepository = theCourseRepository;
	}

	@Override
	@Transactional
	public Course findById(int theId) {
		Course result = courseRepository.findById(theId);
		return result;
		
	}

	@Override
	@Transactional
	public void save(Course theCourse) {
		courseRepository.save(theCourse);
	}

	@Override
	@Transactional
	public void delete(int courseId, String instructor) {
		courseRepository.delete(courseId, instructor);
	}

	@Override
	public ArrayList<Course> findCourseByInstructorLogin(String theInstructor) {
		return (ArrayList<Course>) courseRepository.findInstructor(theInstructor);
	}

	@Override
	@Transactional
	public void update(Course theCourse, String theInstructor) {
		int courseId = theCourse.getCourseID();
		String name = theCourse.getName();
		String description = theCourse.getDescription();
		int year = theCourse.getYear();
		String semester = theCourse.getSemester();
		courseRepository.update(name, description, year, semester, courseId, theInstructor);
	}
	
}
