package cms.service.statistics;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import cms.service.TemplateStatisticStrategy;

public class MinStatisticStrategy extends TemplateStatisticStrategy {
	
	public MinStatisticStrategy() {}
	
	@Override
	public double doActualCalculation(DescriptiveStatistics listOfGrades) {
		return listOfGrades.getMin();
	}
}
