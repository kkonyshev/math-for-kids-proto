package mfk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class NumberStatDaoMock implements NumberStatDao, Serializable {
	private static final long serialVersionUID = 2005638416170769606L;
	
	private Set<NumberStat> numberStatList = new HashSet<NumberStat>();
	
	@Override
	public void updateStat(NumberStat stat) {
		numberStatList.add(stat);
	}

	@Override
	public void updateStatList(Collection<NumberStat> statCollection) {
		numberStatList.addAll(statCollection);
	}

	@Override
	public void removeStat(Long profileId) {
		Iterator<NumberStat> iterator = numberStatList.iterator();
		while (iterator.hasNext()) {
			NumberStat stat = iterator.next();
			if (stat.getProfileId().equals(profileId)) {
				iterator.remove();
			}
		}
	}

	@Override
	public List<NumberStat> getNumberStat(Long profileId) {
		Iterator<NumberStat> iterator = numberStatList.iterator();
		List<NumberStat> res = new ArrayList<NumberStat>();
		while (iterator.hasNext()) {
			NumberStat stat = iterator.next();
			if (stat.getProfileId().equals(profileId)) {
				res.add(stat);
			}
		}
		return res;
	}

	@Override
	public List<NumberStat> getNumberStat(Long profileId, Integer number) {
		Iterator<NumberStat> iterator = numberStatList.iterator();
		List<NumberStat> res = new ArrayList<NumberStat>();
		while (iterator.hasNext()) {
			NumberStat stat = iterator.next();
			if (stat.getProfileId().equals(profileId) && stat.getNumber().equals(number)) {
				res.add(stat);
			}
		}
		return res;
	}

	@Override
	public List<NumberStat> getNumberStat(Long profileId, Collection<Integer> numberCollection) {
		Iterator<NumberStat> iterator = numberStatList.iterator();
		List<NumberStat> res = new ArrayList<NumberStat>();
		while (iterator.hasNext()) {
			NumberStat stat = iterator.next();
			if (stat.getProfileId().equals(profileId) && numberCollection.contains(stat.getNumber())) {
				res.add(stat);
			}
		}
		return res;
	}


}
