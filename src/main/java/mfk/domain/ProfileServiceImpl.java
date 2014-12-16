package mfk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Бизнесс-логика работы с профилями
 * 
 * @author kkonyshev
 *
 */
//TODO add find/remove/update validations
@Component
@Service
public class ProfileServiceImpl implements ProfileService, Serializable {	
	private static final long serialVersionUID = 5499676328477794119L;

	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private NumberStatDao numberStatDao;
	
	@Override
	public Profile findProfileByName(String profileName) {
		return profileDao.find(profileName);
	}

	@Override
	public List<Profile> getProfileList() {
		return profileDao.listAll();
	}
	
	@Override
	public Integer profileCount() {
		return profileDao.size();
	}
	
	@Override
	public void save(Profile profile) {
		profileDao.save(profile);
	}
	
	@Override
	public void remove(Profile profile) {
		numberStatDao.removeStat(profile.getId());
		profileDao.remove(profile);
	}

	@Override
	public void setNumberStat(Profile profile, Integer number, Integer count) {
		NumberStat stat = new NumberStat();
		stat.setProfileId(profile.getId());
		stat.setNumber(number);
		stat.setCount(count);
		setNumberStat(stat);
	}
	
	@Override
	public void setNumberStat(NumberStat numberStat) {
		numberStatDao.updateStat(numberStat);
	}

	@Override
	public List<NumberStat> getNumberStat(Profile profile) {
		return numberStatDao.getNumberStat(profile.getId());
	}

	@Override
	public List<Integer> getLearnedNumber(Profile profile) {
		List<NumberStat> stat = numberStatDao.getNumberStat(profile.getId());
		List<Integer> batch = new ArrayList<Integer>();
		for (NumberStat numberStat: stat) {
			if (numberStat.getCount().compareTo(Profile.DEFAUL_VIEW_COUNT)>=0) {
				batch.add(numberStat.getNumber());
			}
		}
		return batch;
	}

	@Override
	public List<Integer> getNextBatchToLearn(Profile profile) {
		List<Integer> batch = new ArrayList<Integer>();
		List<NumberStat> statList = numberStatDao.getNumberStat(profile.getId());
		Map<Integer, Integer> viewMap = new HashMap<Integer, Integer>();
		for (NumberStat statItem: statList) {
			viewMap.put(statItem.getNumber(), statItem.getCount());
		}
		for (Integer i=Profile.DEFAUL_MIN_NUMBER; i<=Profile.DEFAUL_MAX_NUMBER; i++) {
			Integer count = viewMap.get(i);
			if (count==null || count.compareTo(Profile.DEFAUL_VIEW_COUNT)<0) {
				batch.add(i);
			}
			if (Integer.valueOf(batch.size())>=Profile.DEFAUL_BATCH_NUMBER) {
				break;
			}
		}
		return batch;
	}

	@Override
	public void setNumberStat(Collection<NumberStat> numberStatCollection) {
		numberStatDao.updateStatList(numberStatCollection);
	}

	@Override
	public void increaseNumberStat(Profile profile, Integer number) {
		Long profileId = profile.getId();
		NumberStat stat = numberStatDao.getNumberStat(profileId, number);
		stat.setCount(stat.getCount()+1);
		numberStatDao.updateStat(stat);
	}
	
	/*
	 * 
	 */

	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}
	
	public void setNumberStatDao(NumberStatDao numberStatDao) {
		this.numberStatDao = numberStatDao;
	}


}
