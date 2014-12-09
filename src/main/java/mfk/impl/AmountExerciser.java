package mfk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mfk.api.Exerciser;
import mfk.api.Progress;

/**
 * 
 * @author kkonyshev
 *
 */
@Component
public class AmountExerciser implements Exerciser<AmountContext> {
	
	@Autowired
	private Progress<AmountContext> progress;
	
	@Override
	public AmountContext generateExercise() {
		List<AmountContext> batch = progress.getNumberBatch();
		if (!batch.isEmpty()) {
			return batch.iterator().next();
		}
		throw new RuntimeException("no more numbers to learn");
	}

	@Override
	public void setProgress(Progress<AmountContext> progress) {
		this.progress = progress;
	}
	
}
