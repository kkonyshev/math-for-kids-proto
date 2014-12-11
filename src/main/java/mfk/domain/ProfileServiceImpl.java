package mfk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
		numberStatDao.updateStatList(profile.getNumberCount());
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
		List<NumberStat> statList = numberStatDao.getNumberStat(profile.getId());
		for (NumberStat statItem: statList) {
			
		}
		return null;
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
