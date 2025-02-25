package cms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import cms.controller.CourseController;
import cms.entity.Course;
import cms.entity.StudentRegistration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@AutoConfigureMockMvc
class TestCourseController {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	CourseController courseController;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testCourseControllerIsNotNull() {
		Assertions.assertNotNull(courseController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	
	@WithMockUser(value = "john")
	@Test 
	void testShowCoursesReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/courses"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/courses"));		
	}

	@WithMockUser(value = "john")
	@Test 
	void testShowStudentsReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/ShowStudents?courseId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/students"));	
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testAddCourseFormReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/AddCourseForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/course-form"));	
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testAddStudentFormReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/AddStudentForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/student-form"));	
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testUpdateCourseFormReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/UpdateCourseForm?courseId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/course-form"));	
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testUpdateGradeFormReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/ShowStudents?courseId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/students"));	
		mockMvc.perform(get("/courses/UpdateGradeForm?studentId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/grade-form"));	
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testUpdateStudentFormReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/UpdateStudentForm?studentId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/student-form"));	
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testSaveCourseReturnsPage() throws Exception {
		
	    Course course = new Course(0,"Software Engineering","MYY803",2021,"summer","john");
	    //when(course.getCourseID()).thenReturn(new Course(0,"Software Engineering","MYY803",2021,"summer","john"));
	    //courseSelected = course;	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("courseID", Integer.toString(course.getCourseID()));
	    multiValueMap.add("name", course.getName());
	    multiValueMap.add("description", course.getDescription());
	    multiValueMap.add("year", Integer.toString(course.getYear()));
	    multiValueMap.add("semester", course.getSemester());
	    multiValueMap.add("instructor", course.getInstructor());
	    
		mockMvc.perform(
				post("/courses/SaveCourse")
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/courses/courses"));	
	} 
	
	@WithMockUser(value = "john")
	@Test 
	void testSaveStudentReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/ShowStudents?courseId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/students"));	
		
	    Course course = new Course(1,"Software Engineering","MYY803",2021,"summer","john");
		StudentRegistration student = new StudentRegistration(1,"Stelios Dimitriou",2018,0,0,course);
	    //when(course.getCourseID()).thenReturn(new Course(0,"Software Engineering","MYY803",2021,"summer","john"));
	    //courseSelected = course;	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("studentID", Integer.toString(student.getStudentID()));
	    multiValueMap.add("name", student.getName());
	    multiValueMap.add("yearofregistration", Integer.toString(student.getYearofregistration()));
	    multiValueMap.add("projectgrade", Integer.toString(student.getProjectgrade()));
	    multiValueMap.add("examgrade", Integer.toString(student.getExamgrade()));
	    
		mockMvc.perform(
				post("/courses/SaveStudent")
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/courses/ShowStudents?courseId=1"));	
	} 
	
	@WithMockUser(value = "john")
	@Test 
	void testSaveGradeReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/ShowStudents?courseId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/students"));	
		
	    Course course = new Course(1,"Software Engineering","MYY803",2021,"summer","john");
		StudentRegistration student = new StudentRegistration(1,"Stelios Dimitriou",2018,10,10,course);
	    //when(course.getCourseID()).thenReturn(new Course(0,"Software Engineering","MYY803",2021,"summer","john"));
	    //courseSelected = course;	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("projectgrade", Integer.toString(student.getProjectgrade()));
	    multiValueMap.add("examgrade", Integer.toString(student.getExamgrade()));
	    
		mockMvc.perform(
				post("/courses/SaveGrade")
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/courses/ShowStudents?courseId=1"));	
	} 
	
	@WithMockUser(value = "john")
	@Test 
	void testDeleteCourseReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/AddCourseForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/course-form"));	
		Course course = new Course(999,"Software Engineering","MYY803",2021,"summer","john");
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("courseID", Integer.toString(course.getCourseID()));
	    multiValueMap.add("name", course.getName());
	    multiValueMap.add("description", course.getDescription());
		multiValueMap.add("year", Integer.toString(course.getYear()));
	    multiValueMap.add("semester", course.getSemester());
	    multiValueMap.add("instructor", course.getInstructor());
	    mockMvc.perform(
				post("/courses/SaveCourse")
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/courses/courses"));	
		mockMvc.perform(get("/courses/DeleteCourse?courseId=999"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/courses/courses"));	
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testDeleteStudentReturnsPage() throws Exception {
		
		mockMvc.perform(get("/courses/ShowStudents?courseId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/students"));	
		
		mockMvc.perform(get("/courses/AddStudentForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/student-form"));	
		
		Course course = new Course(1,"Software Engineering","MYY803",2021,"summer","john");
		StudentRegistration student = new StudentRegistration(999,"Stelios Dimitriou",2018,10,10,course);
		
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("studentID", Integer.toString(student.getStudentID()));
	    multiValueMap.add("name", student.getName());
	    multiValueMap.add("yearofregistration", Integer.toString(student.getYearofregistration()));
	    multiValueMap.add("projectgrade", Integer.toString(student.getProjectgrade()));
	    multiValueMap.add("examgrade", Integer.toString(student.getExamgrade()));
	    
	    mockMvc.perform(
				post("/courses/SaveStudent")
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/courses/ShowStudents?courseId=1"));	
	    
		mockMvc.perform(
				get("/courses/DeleteStudent?studentId=999"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/courses/ShowStudents?courseId=1"));	
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testShowStatsForCourseReturnsPage3() throws Exception {
		mockMvc.perform(get("/courses/courses"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/courses"));	
		mockMvc.perform(get("/courses/ShowStatsForCourse?courseId=3"))
				.andExpect(status().isOk())
				.andExpect(view().name("/courses/statistics"));		
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testShowStatsForCourseReturnsPage2() throws Exception {
		mockMvc.perform(get("/courses/courses"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/courses"));	
		mockMvc.perform(get("/courses/ShowStatsForCourse?courseId=2"))
				.andExpect(status().isOk())
				.andExpect(view().name("/courses/statistics"));		
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testShowStatsForCourseReturnsPage1() throws Exception {
		mockMvc.perform(get("/courses/courses"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/courses"));		
		mockMvc.perform(get("/courses/ShowStatsForCourse?courseId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("/courses/statistics"));		
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testShowStatsForCourseAltReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/courses"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/courses"));	
		mockMvc.perform(get("/courses/ShowStudents?courseId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/students"));
		mockMvc.perform(get("/courses/ShowStatsForCourseAlt"))
				.andExpect(status().isOk())
				.andExpect(view().name("/courses/statistics"));		
	}
	
	@WithMockUser(value = "john")
	@Test 
	void testCalculateFinalGradesReturnsPage() throws Exception {
		mockMvc.perform(get("/courses/courses"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/courses"));	
		mockMvc.perform(get("/courses/ShowStudents?courseId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses/students"));
		mockMvc.perform(get("/courses/CalculateFinalGrades?courseId=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("/courses/final-grades"));		
	}
}
