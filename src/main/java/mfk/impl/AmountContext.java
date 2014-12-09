package mfk.impl;

import mfk.api.TrainingContext;

/**
 * КОнтекст изучения количеств
 * 
 * @author kkonyshev
 *
 */
public class AmountContext implements TrainingContext {
	
	private Integer number;
	
	public AmountContext(Integer number) {
		this.number = number;
	}
	
	@Override
	public String getContextType() {
		return "Изучение количеств";
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
