package cms.service.statistics;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import cms.service.TemplateStatisticStrategy;

public class PercentileStatisticStrategy extends TemplateStatisticStrategy {

	private int percentile;
	public PercentileStatisticStrategy(int percentile) {
		this.percentile = percentile;
	}
	
	@Override
	public double doActualCalculation(DescriptiveStatistics listOfGrades) {
		return listOfGrades.getPercentile(percentile);
	}
}
