package cms.service.statistics;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import cms.service.TemplateStatisticStrategy;

public class SkewnessStatisticStrategy extends TemplateStatisticStrategy {
	
	public SkewnessStatisticStrategy() {}

	@Override
	public double doActualCalculation(DescriptiveStatistics listOfGrades) {
		return listOfGrades.getSkewness();
	}
}
