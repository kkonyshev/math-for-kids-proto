package mfk.domain;

import java.util.Collection;
import java.util.List;

public interface NumberStatDao {
	void updateStat(NumberStat stat);
	void updateStatList(Collection<NumberStat> statCollection);
	void removeStat(Long profileId);
	List<NumberStat> getNumberStat(Long profileId);
	List<NumberStat> getNumberStat(Long profileId, Integer number);
	List<NumberStat> getNumberStat(Long profileId, Collection<Integer> numberCollection);
}
