package cms.service.statistics;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import cms.service.TemplateStatisticStrategy;

public class MedianStatisticStrategy extends TemplateStatisticStrategy {
	
	public double calculate(ArrayList<Integer> listOfGrades) {
		Collections.sort(listOfGrades);
		if (listOfGrades.size() % 2 == 1) {
			return listOfGrades.get(listOfGrades.size()/2);
		} else {
			return listOfGrades.get((listOfGrades.size()/2) - 1);
		}
	}

	@Override
	protected double doActualCalculation(DescriptiveStatistics list) {
		return 0;
	}
}
