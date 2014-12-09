package mfk.api;

/**
 * Контекста для изучения
 * 
 * @author kkonyshev
 *
 * @param <Context>
 */
public interface Instructor<C extends TrainingContext> {
	/**
	 * Получить контекст для изучения
	 * 
	 * @return
	 */
	C generateContext();
	
	void setProgress(Progress<C> progressBean);
}
