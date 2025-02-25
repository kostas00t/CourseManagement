package cms.service;

public class OverallStudentGrades {

	public static double calc(int projectGrade, int examGrade, double examWeight) {
		return examGrade*examWeight + projectGrade*(1-examWeight);	
	}
}
