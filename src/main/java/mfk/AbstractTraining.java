package mfk;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * Тренинг
 * 
 * @author kkonyshev
 *
 */
public abstract class AbstractTraining {
	
	public static final Integer MIN_NUMBER = 0;
	public static final Integer MAX_NUMBER = 100;
	
	public static final Integer SUGGESTTED_STEP = 5;
	public static final Integer VIEW_COUNT 		= 5;
	
	private String name;
	public Object picture;
	private Map<Integer, Set<Action>> progressMap = new TreeMap<Integer, Set<Action>>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Integer> getNextBatch() {
		Set<Integer> batch = new HashSet<Integer>();
		for (int i=MIN_NUMBER; i<=MAX_NUMBER && batch.size()!=SUGGESTTED_STEP; i++){
			Set<Action> actionSet = progressMap.get(i);
			if (actionSet==null || actionSet.size()<VIEW_COUNT) {
				batch.add(i);
			}
		}
		return batch;
	}
	
	protected void checkRange(Integer number) {
		if (number==null || number < MIN_NUMBER || number > MAX_NUMBER) {
			throw new IllegalArgumentException(name);
		}
	}
	
	public void updateProgressItem(Integer number, ActionType actionType) {
		checkRange(number);
		Set<Action> item = progressMap.get(number);
		if (item==null) {
			progressMap.put(number, new HashSet<Action>());
		}
		Action a = new Action(actionType);
		progressMap.get(number).add(a);
	}
	
	public Set<Action> getProgressFor(Integer number) {
		checkRange(number);
		Set<Action> item = progressMap.get(number);
		if (item==null) {
			return Collections.<Action>emptySet();
		}
		return item;
	}
	
	public String printProgressStat() {
		StringBuilder sb = new StringBuilder();
		for (Entry<Integer, Set<Action>> entry: progressMap.entrySet()) {
			sb.append("\n");
			sb.append(entry.getKey()).append(": [");
			for (Action a: entry.getValue()) {
				sb.append(a.getActionDate()).append("-").append(a.getActionType().name()).append(";");
			}
			sb.append("]");
		}
		return sb.toString();
	}
	
	public Integer getProgressPercentage() {
		Float total = 0f;
		for (Integer i=MIN_NUMBER; i<=MAX_NUMBER; i++) {
			Set<Action> actionSet = progressMap.get(i);
			if (actionSet==null) {
				actionSet = Collections.<Action>emptySet();
			}
			Integer count = Math.min(VIEW_COUNT, actionSet.size());
			Float numberWeight = Float.valueOf(count)/VIEW_COUNT;
			total = total + numberWeight;
		}
		return total.intValue();
	}
	
	/**/
	@Override
	public String toString() {
		return "Тренировка{" + name + "}";
	}
}
