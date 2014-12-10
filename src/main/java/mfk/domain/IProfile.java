package mfk.domain;

import java.util.List;

/**
 * Профиль обучающегося
 * 
 * @author kkonyshev
 *
 */
public interface IProfile {
	public static final Integer DEFAUL_MIN_NUMBER = 0;
	public static final Integer DEFAUL_MAX_NUMBER = 100;
	public static final Integer DEFAUL_VIEW_COUNT = 5;
	Long getId();
	String getName();
	void setName(String name);
	List<NumberStat> getNumberCount();
}
