package mfk.impl;

import mfk.api.Executor;
import mfk.api.Training;

public class AmountTraining extends Training<AmountContext> {

	public static void main(String[] argv) {
		AmountTraining tr = new AmountTraining();
		Executor<AmountContext> executor = new AmountContextExecutor();
		tr.setExecutor(executor);
		AmountProgress progress = new AmountProgress();
		tr.setProgess(progress);
		AmountExerciser amountExercise = new AmountExerciser();
		amountExercise.setProgress(progress);
		tr.setExercis(amountExercise);
		AmountInstructor learner = new AmountInstructor();
		learner.setProgress(progress);
		tr.setLearner(learner);
		
		for (int i=0; i<150; i++) {
			AmountContext context = tr.getLearner().generateContext();
			tr.getExecutor().execute(context);
			tr.getProgess().increaseProgress(context);
			tr.getProgess().printProgress();
		}
	}
}
