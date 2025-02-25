package cms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import cms.entity.Course;
import cms.service.CourseService;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestCourseService {

	@Autowired 
	CourseService courseService;
	
	@Test
	void testCourseDAOIsNotNull() {
		Assertions.assertNotNull(courseService);
	}

	@Test
	void testFindByIdReturnsCourse() {
		Course storedCourse = courseService.findById(1);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals(1, storedCourse.getCourseID());
		Assertions.assertEquals("Software Engineering", storedCourse.getName());
		//Assertions.assertEquals("MYY803", storedCourse.getDescription());
		Assertions.assertEquals(2022, storedCourse.getYear());
		Assertions.assertEquals("summer", storedCourse.getSemester());
		Assertions.assertEquals("john", storedCourse.getInstructor());
	}
	
}
