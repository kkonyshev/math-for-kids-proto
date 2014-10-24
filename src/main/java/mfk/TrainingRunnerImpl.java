package mfk;

public class TrainingRunnerImpl extends Thread implements TrainingRunner {

	private AbstractTraining training;
	
	public TrainingRunnerImpl(AbstractTraining training) {
		this.training = training;
	}

	@Override
	public void run() {
		System.out.println("Starting training: " + training);
		for (int i=0; i<AbstractTraining.VIEW_COUNT; i++) {
			//training.
		}
	}

	public AbstractTraining getTraining() {
		return training;
	}

	public void setTraining(AbstractTraining training) {
		this.training = training;
	}

}
