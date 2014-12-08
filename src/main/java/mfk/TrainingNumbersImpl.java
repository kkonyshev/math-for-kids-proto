package mfk;

import java.util.List;

/**
 * Изучения количеств
 * 
 * @author kkonyshev
 *
 */
public class TrainingNumbersImpl extends AbstractTraining<Integer> {
	public void checkRange(Integer number) {
		if (number==null || number < MIN_NUMBER || number > MAX_NUMBER) {
			throw new IllegalArgumentException(name);
		}
	}
	
	@Override
	protected Integer createNextContext() {
		List<Integer> batch = getNextBatch();
		if (!batch.isEmpty()) {
			return batch.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected void process(Integer number) {
		System.out.println("Отображаем число: " + number);
	}
}
