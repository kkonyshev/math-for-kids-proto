package mfk.api;

/**
 * Исполнитель контекста
 * 
 * @author kkonyshev
 *
 */
public interface Executor<C extends TrainingContext> {
	void execute(C context);
}
