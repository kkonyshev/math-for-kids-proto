package mfk;

import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Класс формирования статистики прохождения тренеровок
 * 
 * @author kkonyshev
 *
 */
public class TrainingStatisticHelper {

	@SuppressWarnings("unused")
	public static <T extends AbstractTraining<?>> String printProgressStatGraph(T training) {
		
		StringBuilder sb = new StringBuilder();
		for (Entry<?, Set<Action>> entry: training.getProgressMap().entrySet()) {
			Set<Action> actionSet = entry.getValue();
			sb.append(entry.getKey()).append(":");
			for (Action a: actionSet) {
				sb.append("-");
			}
			sb.append("(").append(actionSet.size()).append(")");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static <T extends AbstractTraining<?>> Integer getProgressPercentage(T training) {
		Float total = 0f;
		for (Integer i=AbstractTraining.MIN_NUMBER; i<=AbstractTraining.MAX_NUMBER; i++) {
			Set<Action> actionSet = training.getProgressMap().get(i);
			if (actionSet==null) {
				actionSet = Collections.<Action>emptySet();
			}
			Integer count = Math.min(AbstractTraining.VIEW_COUNT, actionSet.size());
			Float numberWeight = Float.valueOf(count)/AbstractTraining.VIEW_COUNT;
			total = total + numberWeight;
		}
		return total.intValue();
	}
	
	public static <T extends AbstractTraining<?>> String printProgressStat(T training) {
		StringBuilder sb = new StringBuilder();
		for (Entry<?, Set<Action>> entry: training.getProgressMap().entrySet()) {
			sb.append("\n");
			sb.append(entry.getKey()).append(": [");
			for (Action a: entry.getValue()) {
				sb.append(a.getActionDate()).append("-").append(a.getActionType().name()).append(";");
			}
			sb.append("]");
		}
		return sb.toString();
	}
}
