package mfk;

import java.util.List;

public class TrainingRunnerImpl extends Thread implements TrainingRunner {

	private AbstractTraining training;
	
	public TrainingRunnerImpl(AbstractTraining training) {
		this.training = training;
	}

	@Override
	public void run() {
		System.out.println("Starting training: " + training);
		try {
			List<Integer> batch = training.getNextBatch();
			for (Integer number: batch) {
				process(number);
				training.updateProgressItem(number, ActionType.View);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void process(Integer number) throws InterruptedException {
		System.out.println("Отображаем число: " + number);
		Thread.sleep(30);
	}

	public AbstractTraining getTraining() {
		return training;
	}
}
