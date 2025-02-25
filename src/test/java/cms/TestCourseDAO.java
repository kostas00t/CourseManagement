package cms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import cms.dao.*;
import cms.entity.Course;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestCourseDAO {
	@Autowired 
	CourseDAO courseDAO;
	
	@Test
	void testCourseDAOIsNotNull() {
		Assertions.assertNotNull(courseDAO);
	}

	@Test
	void testFindByIdReturnsCourse() {
		Course storedCourse = courseDAO.findById(1);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals(1, storedCourse.getCourseID());
		Assertions.assertEquals("Software Engineering", storedCourse.getName());
		//Assertions.assertEquals("MYY803", storedCourse.getDescription());
		Assertions.assertEquals(2022, storedCourse.getYear());
		Assertions.assertEquals("summer", storedCourse.getSemester());
		Assertions.assertEquals("john", storedCourse.getInstructor());
	}
		
}
