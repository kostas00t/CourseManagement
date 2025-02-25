package cms.service.statistics;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import cms.service.TemplateStatisticStrategy;

public class StandardDeviationStatisticStrategy extends TemplateStatisticStrategy {
	
	public StandardDeviationStatisticStrategy() {}
	
	@Override
	public double doActualCalculation(DescriptiveStatistics listOfGrades) {
		return listOfGrades.getStandardDeviation();
	}
}
