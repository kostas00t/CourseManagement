package cms.service.statistics;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import cms.service.TemplateStatisticStrategy;

public class MaxStatisticStrategy extends TemplateStatisticStrategy {
	
	public MaxStatisticStrategy() {}
	
	@Override
	public double doActualCalculation(DescriptiveStatistics listOfGrades) {
		return listOfGrades.getMax();
	}
}
