package cms.service.statistics;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import cms.service.TemplateStatisticStrategy;

public class VarianceStatisticStrategy extends TemplateStatisticStrategy {
	
	public VarianceStatisticStrategy() {}
	
	@Override
	public double doActualCalculation(DescriptiveStatistics listOfGrades) {
		return listOfGrades.getVariance();
	}
}