package mfk.api;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Training<C extends TrainingContext> {
	@Autowired
	private Progress<C> progess;
	@Autowired
	private Exerciser<C> exercis;
	@Autowired
	private Instructor<C> learner;
	@Autowired
	private Executor<C> executor;
	
	public Progress<C> getProgess() {
		return progess;
	}
	public void setProgess(Progress<C> progess) {
		this.progess = progess;
	}
	public Exerciser<C> getExercis() {
		return exercis;
	}
	public void setExercis(Exerciser<C> exercis) {
		this.exercis = exercis;
	}
	public Instructor<C> getLearner() {
		return learner;
	}
	public void setLearner(Instructor<C> learner) {
		this.learner = learner;
	}
	public Executor<C> getExecutor() {
		return executor;
	}
	public void setExecutor(Executor<C> executor) {
		this.executor = executor;
	}
}
