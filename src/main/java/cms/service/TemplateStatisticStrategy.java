package cms.service;

import cms.entity.StudentRegistration;
import cms.service.statistics.KurtosisStatisticStrategy;
import cms.service.statistics.MaxStatisticStrategy;
import cms.service.statistics.MeanStatisticStrategy;
import cms.service.statistics.MedianStatisticStrategy;
import cms.service.statistics.MinStatisticStrategy;
import cms.service.statistics.PercentileStatisticStrategy;
import cms.service.statistics.SkewnessStatisticStrategy;
import cms.service.statistics.StandardDeviationStatisticStrategy;
import cms.service.statistics.VarianceStatisticStrategy;

import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public abstract class TemplateStatisticStrategy implements StatisticStrategy {

	
	public TemplateStatisticStrategy() {}
	
	@Override
	public double[] calculateStatistic(ArrayList<StudentRegistration> students, String type) {
		double resultP = 0;
		double resultE = 0;
		DescriptiveStatistics listP = prepareDataSet(students, "Project");
		DescriptiveStatistics listE = prepareDataSet(students, "Exam");
		if (type.equals("mean")) {
			MeanStatisticStrategy mss = new MeanStatisticStrategy();
			resultP = mss.doActualCalculation(listP);
			resultE = mss.doActualCalculation(listE);
		} else if (type.equals("min")) {
			MinStatisticStrategy mss = new MinStatisticStrategy();
			resultP = mss.doActualCalculation(listP);
			resultE = mss.doActualCalculation(listE);
		} else if (type.equals("max")) {
			MaxStatisticStrategy mss = new MaxStatisticStrategy();
			resultP = mss.doActualCalculation(listP);
			resultE = mss.doActualCalculation(listE);
		} else if (type.equals("std")) {
			StandardDeviationStatisticStrategy mss = new StandardDeviationStatisticStrategy();
			resultP = mss.doActualCalculation(listP);
			resultE = mss.doActualCalculation(listE);
		} else if (type.equals("var")) {
			VarianceStatisticStrategy mss = new VarianceStatisticStrategy();
			resultP = mss.doActualCalculation(listP);
			resultE = mss.doActualCalculation(listE);
		} else if (type.equals("per95")) {
			PercentileStatisticStrategy mss = new PercentileStatisticStrategy(95);
			resultP = mss.doActualCalculation(listP);
			resultE = mss.doActualCalculation(listE);
		} else if (type.equals("per70")) {
			PercentileStatisticStrategy mss = new PercentileStatisticStrategy(70);
			resultP = mss.doActualCalculation(listP);
			resultE = mss.doActualCalculation(listE);
		} else if (type.equals("skew")) {
			SkewnessStatisticStrategy mss = new SkewnessStatisticStrategy();
			resultP = mss.doActualCalculation(listP);
			resultE = mss.doActualCalculation(listE);
		} else if (type.equals("kur")) {
			KurtosisStatisticStrategy mss = new KurtosisStatisticStrategy();
			resultP = mss.doActualCalculation(listP);
			resultE = mss.doActualCalculation(listE);
		} else if (type.equals("med")) {
			MedianStatisticStrategy mss = new MedianStatisticStrategy();
			ArrayList<Integer> medListP = new ArrayList<>();
			ArrayList<Integer> medListE = new ArrayList<>();
			for (StudentRegistration s : students) {
				int pgrade = s.getProjectgrade();
				int egrade = s.getExamgrade();
				medListP.add(pgrade);
				medListE.add(egrade);
			}
			resultP = mss.calculate(medListP);
			resultE = mss.calculate(medListE);
		}
		double list[] = {resultP,resultE};
		return list;
	}
	
	private static DescriptiveStatistics prepareDataSet(ArrayList<StudentRegistration> students, String gradeType) {
		DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();

		if (gradeType.equals("Project")) {
			for (StudentRegistration s : students) {
				double grade = s.getProjectgrade();
				descriptiveStatistics.addValue(grade);
			}
		} else if (gradeType.equals("Exam")) {
			for (StudentRegistration s : students) {
				double grade = s.getExamgrade();
				descriptiveStatistics.addValue(grade);
			}
		}
		return descriptiveStatistics;
	}

	protected abstract double doActualCalculation(DescriptiveStatistics list);
	
}
