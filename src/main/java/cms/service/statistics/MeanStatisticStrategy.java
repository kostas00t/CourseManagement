package cms.service.statistics;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import cms.service.TemplateStatisticStrategy;

public class MeanStatisticStrategy extends TemplateStatisticStrategy {

	
	public MeanStatisticStrategy() {}
	
	@Override
	public double doActualCalculation(DescriptiveStatistics listOfGrades) {
		return listOfGrades.getMean();
	}

}
