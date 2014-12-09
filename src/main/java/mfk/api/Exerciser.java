package mfk.api;

/**
 * Фабрика генерации упражнений
 * 
 * @author kkonyshev
 *
 * @param <Context>
 */
public interface Exerciser<C extends TrainingContext> {
	/**
	 * Получить пример для проверки
	 * 
	 * @return
	 */
	C generateExercise();
	
	void setProgress(Progress<C> progressBean);
}
