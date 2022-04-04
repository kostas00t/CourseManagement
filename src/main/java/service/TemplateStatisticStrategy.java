package service;

import model.Course;

public abstract class TemplateStatisticStrategy implements StatisticStrategy{

	public TemplateStatisticStrategy () {}
	
	@Override
	public double calculateStatistic(Course a) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void prepareDataSet() {
		// TODO Auto-generated method stub
	}
	
	public abstract void doActualCalculation();
}
