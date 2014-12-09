package mfk.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import mfk.api.Progress;

@Component
public class AmountProgress implements Progress<AmountContext> {

	public static final Integer MIN_AMOUNT 		= 0;
	public static final Integer MAX_AMOUNT 		= 100;
	
	public static final Integer VIEW_COUNT		= 5;
	public static final Integer DEFAULT_STEP 	= 5;
	
	private Map<Integer, Integer> progress = new HashMap<Integer, Integer>();

	public List<AmountContext> getNumberBatch() {
		List<AmountContext> batch = new ArrayList<AmountContext>();
		for (Integer i=getMinAmount(); i<=getMaxAmount(); i++) {
			Integer numCount = progress.get(i);
			Integer maxCount = getCountForNumber(i);
			if (numCount==null || numCount<maxCount) {
				batch.add(new AmountContext(i));
			}
			if (getStep().compareTo(batch.size())<0) {
				break;
			}
		}
		return batch;
	}
	
	@Override
	public void increaseProgress(AmountContext context) {
		Integer number = context.getNumber();
		Integer oldCount = progress.remove(number);
		progress.put(number, oldCount==null ? 0 : ++oldCount);
	}

	@Override
	public void printProgress() {
		System.out.println(progress);
	}
	
	
	//TODO: add user defined value processing
	private Integer getCountForNumber(Integer number) {
		return VIEW_COUNT;
	}
	
	//TODO: add user defined value processing
	private Integer getMinAmount() {
		return MIN_AMOUNT;
	}
	
	//TODO: add user defined value processing
	private Integer getMaxAmount() {
		return MAX_AMOUNT;
	}
	
	//TODO: add user defined value processing
	private Integer getStep() {
		return DEFAULT_STEP;
	}
}
