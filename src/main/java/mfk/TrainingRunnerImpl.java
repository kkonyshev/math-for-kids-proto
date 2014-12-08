package mfk;


public class TrainingRunnerImpl extends Thread implements TrainingRunner {

	private AbstractTraining<?> training;
	
	public TrainingRunnerImpl(AbstractTraining<?> training) {
		this.training = training;
	}

	@Override
	public void run() {
		System.out.println("Starting training: " + training);
		try {
			for (int i=0; i<5 && training.hasNext(); i++) {
				training.process();
				Thread.sleep(30);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public AbstractTraining<?> getTraining() {
		return training;
	}
}
