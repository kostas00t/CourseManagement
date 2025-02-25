package cms.service;

import java.util.ArrayList;

import cms.entity.StudentRegistration;

public interface StatisticStrategy {

	public double[] calculateStatistic(ArrayList<StudentRegistration> students, String type);
}
