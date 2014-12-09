package mfk.api;

import java.util.List;

/**
 * Прогресс изучения контекста
 * 
 * @author kkonyshev
 *
 * @param <C>
 */
public interface Progress<C extends TrainingContext> {
	/**
	 * Получить пачку неизученных номеров
	 * 
	 * @return
	 */
	List<C> getNumberBatch();
	
	void increaseProgress(C context);
	
	void printProgress();
}
