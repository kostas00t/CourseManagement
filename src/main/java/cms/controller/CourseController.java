package cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cms.entity.Course;
import cms.entity.StudentRegistration;
import cms.service.CourseService;
import cms.service.OverallStudentGrades;
import cms.service.StatisticStrategy;
import cms.service.StudentRegistrationService;
import cms.service.statistics.KurtosisStatisticStrategy;
import cms.service.statistics.MaxStatisticStrategy;
import cms.service.statistics.MeanStatisticStrategy;
import cms.service.statistics.MedianStatisticStrategy;
import cms.service.statistics.MinStatisticStrategy;
import cms.service.statistics.PercentileStatisticStrategy;
import cms.service.statistics.SkewnessStatisticStrategy;
import cms.service.statistics.StandardDeviationStatisticStrategy;
import cms.service.statistics.VarianceStatisticStrategy;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentRegistrationService studentRegistrationService;
	
	private Course courseSelected;
	private StudentRegistration studentSelected;
	private String currentInstructorName;
	private ArrayList<StudentRegistration> theStudents;
	private ArrayList<Course> theCourses;
	private boolean addOrUpdateFlag; // true -> add, false -> update
	private double weight = 0.7;
	
	public CourseController(CourseService theCourseService, StudentRegistrationService theStudentRegistrationService) {
		courseService = theCourseService;
		studentRegistrationService = theStudentRegistrationService;
	}
	
	
	@RequestMapping("/courses")
	public String listCourses(Model theModel) {
		
		currentInstructorName = SecurityContextHolder.getContext().getAuthentication().getName();
		theCourses = courseService.findCourseByInstructorLogin(currentInstructorName);
		theModel.addAttribute("courses", theCourses);
		
		return "courses/courses";
	}
	
	
	@RequestMapping("/ShowStudents")
	public String listStudents(@RequestParam("courseId") int theId, Model theModel) {
		
		Course theCourse = courseService.findById(theId);
		courseSelected = theCourse;
		theStudents = studentRegistrationService.findRegistrationsByCourseId(theCourse);
		theModel.addAttribute("studentreg", theStudents);
		
		return "courses/students";			
	}
	
	
	@RequestMapping("/AddCourseForm")
	public String showFormForAddCourse(Model theModel) {
		
		Course theCourse = new Course();
		addOrUpdateFlag = true;
		theModel.addAttribute("course", theCourse);
		theModel.addAttribute("formtype", "Add New Course");
		
		return "courses/course-form";
	}
	
	
	@RequestMapping("/AddStudentForm")
	public String showFormForAddStudent(Model theModel) {
		
		StudentRegistration theStudent = new StudentRegistration();
		theStudent.setProjectgrade(0);
		theStudent.setExamgrade(0);
		addOrUpdateFlag = true;
		theModel.addAttribute("student", theStudent);
		theModel.addAttribute("formtype", "Add New Student");
		
		return "courses/student-form";
	}
	

	@RequestMapping("/UpdateCourseForm")
	public String showFormForUpdateCourse(@RequestParam("courseId") int theId, Model theModel) {
		
		Course theCourse = courseService.findById(theId);
		courseSelected = theCourse;
		addOrUpdateFlag = false;
		theModel.addAttribute("course", theCourse);
		theModel.addAttribute("formtype", "Update Existing Course");

		return "courses/course-form";			
	}
	
	@RequestMapping("/UpdateStudentForm")
	public String showFormForUpdateStudent(@RequestParam("studentId") int theStudentId, Model theModel) {
		
		StudentRegistration theStudent = studentRegistrationService.findById(theStudentId, courseSelected);
		studentSelected = theStudent;
		addOrUpdateFlag = false;
		theModel.addAttribute("student", theStudent);
		theModel.addAttribute("formtype", "Update Existing Student");
		
		return "courses/student-form";			
	}
	
	
	@RequestMapping("/UpdateGradeForm")
	public String showFormForUpdateStudentGrade(@RequestParam("studentId") int theStudentId, Model theModel) {
		
		StudentRegistration theStudent = studentRegistrationService.findById(theStudentId, courseSelected);
		studentSelected = theStudent;
		addOrUpdateFlag = false;
		theModel.addAttribute("student", theStudent);
			
		return "courses/grade-form";			
	}
	
	
	@RequestMapping("/SaveCourse")
	public String saveCourse(@ModelAttribute("course") Course theCourse, Model theModel) {
		
		theCourse.setInstructor(currentInstructorName);
		if (addOrUpdateFlag) { //add
			courseService.save(theCourse);
		} else { //update
			theCourse.setCourseID(courseSelected.getCourseID());
			courseService.update(theCourse, currentInstructorName);
		}
		
		return "redirect:/courses/courses";
	}
	
	
	@RequestMapping("/SaveStudent")
	public String saveStudent(@ModelAttribute("student") StudentRegistration theStudent, Model theModel) {
		
		theStudent.setCourseID(courseSelected);
		if (addOrUpdateFlag) { //add
			studentRegistrationService.add(theStudent);
		} else { //update
			theStudent.setStudentID(studentSelected.getStudentID());
			studentRegistrationService.updateStudent(theStudent);
		}
		
		String returnAddress = "redirect:/courses/ShowStudents?courseId="+courseSelected.getCourseID();
		return returnAddress;
	}
	
	@RequestMapping("/SaveGrade")
	public String saveGrade(@ModelAttribute("student") StudentRegistration theStudent, Model theModel) {
		
		theStudent.setStudentID(studentSelected.getStudentID());
		studentRegistrationService.updateGrade(theStudent, courseSelected);
		
		String returnAddress = "redirect:/courses/ShowStudents?courseId="+courseSelected.getCourseID();
		return returnAddress;
		
	}
	
	@RequestMapping("/DeleteCourse")
	public String deleteCourse(@RequestParam("courseId") int theId) {
		
		courseService.delete(theId, currentInstructorName);
		
		return "redirect:/courses/courses";
	}
	
	
	@RequestMapping("/DeleteStudent")
	public String deleteStudent(@RequestParam("studentId") int theId) {

		studentRegistrationService.delete(theId, courseSelected);
		
		String returnAddress = "redirect:/courses/ShowStudents?courseId="+courseSelected.getCourseID();
		return returnAddress;
		
	}
	
	@RequestMapping("/CalculateFinalGrades")
	public String calcFinalGrades(Model theModel) {
		int theId = courseSelected.getCourseID();
		Course theCourse = courseService.findById(theId);
		ArrayList<StudentRegistration> theStudentsOfACourse = studentRegistrationService.findRegistrationsByCourseId(theCourse);
		List<String[]> gradesList = new ArrayList<>();
		for (StudentRegistration student : theStudentsOfACourse) {
			int p = student.getProjectgrade();
			int e = student.getExamgrade();
			double finalGrade = OverallStudentGrades.calc(p, e, weight);
			String passOrFail;
			if (finalGrade>=5) {
				passOrFail = "PASS";
			} else {
				passOrFail = "FAIL";
			}
			String[] s = {String.valueOf(student.getStudentID()), student.getName(), String.valueOf(p), String.valueOf(e), String.valueOf(finalGrade), passOrFail};
			gradesList.add(s);
		}
		theModel.addAttribute("courseName", theCourse.getName());
		theModel.addAttribute("grades", gradesList);
		return "/courses/final-grades";
		
	}
	
	
	@RequestMapping("/ShowStatsForCourse")
	public String showStatisticsForSpecificCourse(@RequestParam("courseId") int theId, Model theModel) {
		
		Course theCourse = courseService.findById(theId);
		ArrayList<Course> coursesOfCurrentInstructor = courseService.findCourseByInstructorLogin(currentInstructorName);
		List<String[]> statsList = new ArrayList<>();
		if (coursesOfCurrentInstructor.contains(theCourse)) {
			ArrayList<StudentRegistration> theStudentsOfACourse = studentRegistrationService.findRegistrationsByCourseId(theCourse);
			StatisticStrategy s1 = new MinStatisticStrategy();
			StatisticStrategy s2 = new MaxStatisticStrategy();
			StatisticStrategy s3 = new MeanStatisticStrategy();
			StatisticStrategy s4 = new StandardDeviationStatisticStrategy();
			StatisticStrategy s5 = new VarianceStatisticStrategy();
			StatisticStrategy s6 = new PercentileStatisticStrategy(95);
			StatisticStrategy s7 = new PercentileStatisticStrategy(70);
			StatisticStrategy s8 = new SkewnessStatisticStrategy();
			StatisticStrategy s9 = new KurtosisStatisticStrategy();
			StatisticStrategy s10 = new MedianStatisticStrategy();
			double[] x1 = s1.calculateStatistic(theStudentsOfACourse, "min");
			double[] x2 = s2.calculateStatistic(theStudentsOfACourse, "max");
			double[] x3 = s3.calculateStatistic(theStudentsOfACourse, "mean");
			double[] x4 = s4.calculateStatistic(theStudentsOfACourse, "std");
			double[] x5 = s5.calculateStatistic(theStudentsOfACourse, "var");
			double[] x6 = s6.calculateStatistic(theStudentsOfACourse, "per95");
			double[] x7 = s7.calculateStatistic(theStudentsOfACourse, "per70");
			double[] x8 = s8.calculateStatistic(theStudentsOfACourse, "skew");
			double[] x9 = s9.calculateStatistic(theStudentsOfACourse, "kur");
			double[] x10 = s10.calculateStatistic(theStudentsOfACourse, "med");
			String[] p = {"Project",String.valueOf(theCourse.getCourseID()), theCourse.getName(), String.valueOf(x1[0]), String.valueOf(x2[0]), String.valueOf(x3[0]),
					String.valueOf(x4[0]), String.valueOf(x5[0]), String.valueOf(x6[0]), String.valueOf(x7[0]), String.valueOf(x8[0]), String.valueOf(x9[0]), String.valueOf(x10[0])};
			statsList.add(p);
			String[] e = {"Exam",String.valueOf(theCourse.getCourseID()), theCourse.getName(), String.valueOf(x1[1]), String.valueOf(x2[1]), String.valueOf(x3[1]),
					String.valueOf(x4[1]), String.valueOf(x5[1]), String.valueOf(x6[1]), String.valueOf(x7[1]), String.valueOf(x8[1]), String.valueOf(x9[1]), String.valueOf(x10[1])};
			statsList.add(e);
		} else { // if the instructor requests stats -by changing the request parameter on the url- for a course that's not his, don't show any information about it
			String[] e = {"Not Available", "Not Available", "Not Available", "Not Available", "Not Available", "Not Available", "Not Available", "Not Available",
					"Not Available", "Not Available", "Not Available", "Not Available", "Not Available"};
			statsList.add(e);
		}
		theModel.addAttribute("stats", statsList);
		theModel.addAttribute("courseName", theCourse.getName());
		
		return "/courses/statistics";
	}	
	
	
	@RequestMapping("/ShowStatsForCourseAlt")
	public String showStatisticsForSpecificCourse(Model theModel) {
		
		int theId = courseSelected.getCourseID();
		showStatisticsForSpecificCourse(theId, theModel);
		
		return "/courses/statistics";
	}	
	
}


















