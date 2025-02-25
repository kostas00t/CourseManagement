package cms;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cms.dao.*;
import cms.entity.Course;
import cms.service.CourseService;
import cms.service.CourseServiceImpl;


//@SpringBootTest
//@TestPropertySource(
//  locations = "classpath:application.properties")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
class TestCourseServiceWithMocks {

	@TestConfiguration
    static class CourseServiceImplTestContextConfiguration {
 
        @Bean
        public CourseService courseService() {
            return new CourseServiceImpl();
        }
    }

	@Autowired 
	CourseService courseService;
	
	@MockBean
	CourseDAO courseDAO;
	
	@Test
	void testCourseDAOIsNotNull() {
		Assertions.assertNotNull(courseDAO);
	}

	@Test
	void testFindByIdReturnsEmployee() {
		Mockito.when(courseDAO.findById(1)).thenReturn(new Course(1,"Software Engineering","MYY803",2021,"summer","john"));
		Course storedCourse = courseService.findById(1);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals(1, storedCourse.getCourseID());
		Assertions.assertEquals("Software Engineering", storedCourse.getName());
		Assertions.assertEquals("MYY803", storedCourse.getDescription());
		Assertions.assertEquals(2021, storedCourse.getYear());
		Assertions.assertEquals("summer", storedCourse.getSemester());
		Assertions.assertEquals("john", storedCourse.getInstructor());
	}
}
