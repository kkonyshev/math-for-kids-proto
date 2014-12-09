package mfk.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import mfk.api.Instructor;
import mfk.api.Progress;
import mfk.util.Utils;

@Component
public class AmountInstructor implements Instructor<AmountContext> {

	private Progress<AmountContext> progressBean;

	@Override
	public void setProgress(Progress<AmountContext> progressBean) {
		this.progressBean = progressBean;
	}
	
	@Override
	public AmountContext generateContext() {
		List<AmountContext> batch = progressBean.getNumberBatch();
		if (!batch.isEmpty()) {
			Integer rndIndex = Utils.randInteger(0, batch.size()-1);
			return batch.get(rndIndex);
		}
		throw new RuntimeException("no more numbers to learn");
	}

	
}
