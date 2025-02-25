package cms.service.statistics;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import cms.service.TemplateStatisticStrategy;

public class KurtosisStatisticStrategy extends TemplateStatisticStrategy {
	
	public KurtosisStatisticStrategy() {}

	@Override
	public double doActualCalculation(DescriptiveStatistics listOfGrades) {
		return listOfGrades.getKurtosis();
	}
}
