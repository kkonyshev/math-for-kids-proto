package mfk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Тренировка.<br/>
 * Абстракция описывающяя набор действия на последовательностью чисел.
 * 
 * @author kkonyshev
 *
 */
public abstract class AbstractTraining<Context> {
	
	/**
	 * Минимальное число для изучения
	 */
	public static final Integer MIN_NUMBER = 0;
	/**
	 * Максимальное число для изучения
	 */
	public static final Integer MAX_NUMBER = 100;
	
	/**
	 * Предлагаемое по-умолчанию количство цифр в итерации тренировки
	 */
	public static final Integer SUGGESTTED_STEP = 5;
	
	/**
	 * Количество действий с числом, после которого считается, что число изучено 
	 */
	public static final Integer VIEW_COUNT = 5;
	
	/**
	 * Имя тренировки
	 */
	
	protected String name;
	
	/**
	 * Иконка
	 * TODO
	 */
	protected Object picture;
	
	/**
	 * Карта описывающая процесс прохоздения тренировки.<br/>
	 * Содержит инфомрацию об уже изученных или изучаемых цифрах.<br/>
	 * Цифра -> набор действий (см. {@link mfk.Action})
	 */
	private Map<Context, Set<Action>> progressMap = new TreeMap<Context, Set<Action>>();
	
	/**
	 * Карта исключительных правил.<br/>
	 * Хранит количество необходимых действий (см. {@link mfk.Action}) над цифрами после которых считается, что цифра изучена. 
	 */
	private Map<Integer, Integer> maxActionCountMap = new TreeMap<Integer, Integer>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private Context nextContext;
	
	/**
	 * Метод получения последовательности чисел для изучения.<br/>
	 * Отбирается первых {@link mfk.AbstractTraining.SUGGESTTED_STEP} из диапазона 
	 * {@link mfk.AbstractTraining.MIN_NUMBER} - {@link mfk.AbstractTraining.MAX_NUMBER}, 
	 * количество действия по которым меньше необходимого (см. {@link mfk.AbstractTraining.getMaxActionCount(Integer)})<br/> 
	 * Отобранный список перемешивается случайным образом.
	 * 
	 * @return список чисел
	 */
	public List<Integer> getNextBatch() {
		Set<Integer> batch = new HashSet<Integer>();
		for (int i=MIN_NUMBER; i<=MAX_NUMBER && batch.size()!=SUGGESTTED_STEP; i++){
			if (useInBatch(i)) {
				batch.add(i);
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>(batch);
		Collections.shuffle(list);
		return list;
	}
	
	public Boolean hasNext() {
		return createNextContext()!=null;
	}
	
	
	
	protected abstract Context createNextContext();
	
	/**
	 * Проверка: необходимо ли добавлять число в список для последовательности обучения.
	 * 
	 * @param number
	 * @return
	 * TODO выбирать не максимальное из дефолтного и заданного, а использовать деволтное если не задано исключение!
	 */
	private boolean useInBatch(Integer number) {
		Set<Action> actionSet = progressMap.get(number);
		Integer actionCout = getMaxActionCount(number);
		if (actionSet==null || actionSet.size()<actionCout) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Метод проверки, явлвется ли число корректным для обновлении статистики по нему.<br/>
	 * Число должно находится в диапазоне {@link mfk.AbstractTraining.MIN_NUMBER} - {@link mfk.AbstractTraining.MAX_NUMBER}
	 * 
	 * @param number -- 
	 * @throws IllegalArgumentException -- 
	 */
	abstract void checkRange(Context number);
	
	public void process() {
		Context number = createNextContext();
		checkRange(nextContext);
		process(number);
	}
	
	abstract protected void process(Context number);
	
	public void process(Context number, ActionType actionType) {
		checkRange(number);
		switch (actionType) {
			case View: System.out.println("Отображаем число: " + number);
		}
		updateProgressItem(number, actionType);
	}
	
	/**
	 * Метод обновления статистики прохождения тренировки.<br/>
	 * 
	 * @param number -- число по которому обновляется статистика
	 * @param actionType -- действие (см. {@link mfk.ActionType})
	 */
	protected void updateProgressItem(Context number, ActionType actionType) {
		checkRange(number);
		Set<Action> item = progressMap.get(number);
		if (item==null) {
			progressMap.put(number, new HashSet<Action>());
		}
		Action a = new Action(actionType);
		progressMap.get(number).add(a);
	}
	
	/**
	 * Метод получения статистики прохождения тринировки по заданному числу.
	 * 
	 * @param number
	 * @return
	 */
	public Set<Action> getProgressFor(Context number) {
		checkRange(number);
		Set<Action> item = progressMap.get(number);
		if (item==null) {
			return Collections.<Action>emptySet();
		}
		return item;
	}
	
	/**
	 * Получение количество операция над числом, после которого считается, что число изучено.
	 * 
	 * @param number
	 * @return
	 */
	public Integer getMaxActionCount(Integer number) {
		Integer excludeCount = maxActionCountMap.get(number);
		return excludeCount==null ? VIEW_COUNT : excludeCount;
	}
	
	/**
	 * Переопределение дефолтного количества действий на числом, после которого считается, что число изучено.
	 * 
	 * @param number
	 * @param count
	 */
	public void setMaxActionCount(Integer number, Integer count) {
		maxActionCountMap.put(number, count);
	}
	
	public void clearExcludeNumber(Integer number) {
		maxActionCountMap.remove(number);
	}
	
	/**/
	@Override
	public String toString() {
		return "Тренировка{" + name + "}";
	}
	
	public Map<Context, Set<Action>> getProgressMap() {
		return new TreeMap<Context, Set<Action>>(this.progressMap);
	}
}
