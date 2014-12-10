package mfk.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Бизнесс-логика работы с профилями
 * 
 * @author kkonyshev
 *
 */
//TODO add find/remove/update validations
@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private NumberStatDao numberStatDao;
	
	@Override
	public IProfile findProfileByName(String profileName) {
		return profileDao.find(profileName);
	}
	
	@Override
	public List<IProfile> getProfileList() {
		return profileDao.listAll();
	}
	
	@Override
	public Integer profileCount() {
		return profileDao.size();
	}
	
	@Override
	public void save(IProfile profile) {
		numberStatDao.updateStatList(profile.getNumberCount());
		profileDao.save(profile);
	}
	
	@Override
	public void remove(IProfile profile) {
		numberStatDao.removeStat(profile.getId());
		profileDao.remove(profile);
	}

	@Override
	public void setNumberStat(IProfile profile, Integer number, Integer count) {
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
	public List<NumberStat> getNumberStat(IProfile profile) {
		return numberStatDao.getNumberStat(profile.getId());
	}

	@Override
	public List<Integer> getLearnedNumber(IProfile profile) {
		List<NumberStat> stat = numberStatDao.getNumberStat(profile.getId());
		List<Integer> batch = new ArrayList<Integer>();
		for (NumberStat numberStat: stat) {
			if (numberStat.getCount().compareTo(IProfile.DEFAUL_VIEW_COUNT)>=0) {
				batch.add(numberStat.getNumber());
			}
		}
		return batch;
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
